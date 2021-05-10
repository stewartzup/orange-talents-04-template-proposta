package br.com.zupacademy.proposta.novaproposta;

import java.net.URI;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.proposta.feign.restricao.AnaliseRestricaoClient;
import br.com.zupacademy.proposta.feign.restricao.AnaliseRestricaoRequest;
import feign.FeignException;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

	@Autowired
	private AnaliseRestricaoClient analiseCliente;

	@Autowired
	public PropostaRepository propostaRepository;

	@PostMapping
	public ResponseEntity<PropostaResponse> cadastrarAutor(@RequestBody @Valid PropostaRequest request,
			UriComponentsBuilder uriBuilder) {
		Proposta proposta = request.converterToModel();
		Optional<Proposta> verificaPropostaUnica = propostaRepository.findByDocumento(request.documento);

		if (!verificaPropostaUnica.isPresent()) {
			propostaRepository.save(proposta);

			try {
				analiseCliente.analisaRestricao(new AnaliseRestricaoRequest(proposta));
				proposta.setStatus(StatusProposta.ELEGIVEL);

			} catch (FeignException e) {
				if (e.status() == 422)
					proposta.setStatus(StatusProposta.NAO_ELEGIVEL);
				else
					throw e;
			}

			URI uri = uriBuilder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
			return ResponseEntity.created(uri).body(new PropostaResponse(proposta));
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);

	}
}
