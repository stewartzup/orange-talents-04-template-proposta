package br.com.zupacademy.proposta.novaproposta;

import java.net.URI;
import java.util.Iterator;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.zupacademy.proposta.feign.cartao.CartaoRepository;
import br.com.zupacademy.proposta.feign.cartao.RelacionaCartaoClient;
import br.com.zupacademy.proposta.feign.restricao.AnaliseRestricaoClient;
import br.com.zupacademy.proposta.feign.restricao.AnaliseRestricaoRequest;
import feign.FeignException;
import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController
@RequestMapping("/propostas")

public class PropostaController {
	private final Tracer tracer;

	@Autowired
	private AnaliseRestricaoClient analiseCliente;

	@Autowired
	public PropostaRepository propostaRepository;

	@Autowired
	public RelacionaCartaoClient relacionaCartao;

	@Autowired
	public CartaoRepository cartaoRepository;

	public PropostaController(Tracer tracer) {
		this.tracer = tracer;
	}

	@PostMapping
	public ResponseEntity<PropostaResponse> cadastrarAutor(@RequestBody @Valid PropostaRequest request,
			UriComponentsBuilder uriBuilder) {

		Span activeSpan = tracer.activeSpan();
		activeSpan.setBaggageItem("user.email", "stewart.goncalves@zup.com.br");

		Proposta proposta = request.converterToModel();
		Optional<Proposta> verificaPropostaUnica = propostaRepository.findByDocumento(request.documento);

		if (!verificaPropostaUnica.isPresent()) {
			propostaRepository.save(proposta);
			try {
				analiseCliente.analisaRestricao(new AnaliseRestricaoRequest(proposta));
				proposta.setStatus(StatusProposta.ELEGIVEL);
			}

			catch (FeignException e) {
				if (e.status() == 422)
					proposta.setStatus(StatusProposta.NAO_ELEGIVEL);
				else
					throw e;
			}
			propostaRepository.save(proposta);
			URI uri = uriBuilder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
			return ResponseEntity.created(uri).body(new PropostaResponse(proposta));
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);

	}

	@GetMapping("/{id}")
	public ResponseEntity<PropostaResponse> detalhar(@PathVariable @Valid Long id) {
		Optional<Proposta> procuraProposta = propostaRepository.findById(id);
		if (procuraProposta.isPresent()) {
			return ResponseEntity.ok(new PropostaResponse(procuraProposta.get()));
		}

		return ResponseEntity.notFound().build();

	}

}
