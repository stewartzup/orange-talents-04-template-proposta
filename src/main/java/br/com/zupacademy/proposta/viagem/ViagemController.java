package br.com.zupacademy.proposta.viagem;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.zupacademy.proposta.feign.cartao.Cartao;
import br.com.zupacademy.proposta.feign.cartao.CartaoRepository;
import br.com.zupacademy.proposta.feign.cartao.RelacionaCartaoClient;
import br.com.zupacademy.proposta.feign.cartao.bloqueiocartao.BloqueioController;

@RestController
@RequestMapping("/api/viagem")
public class ViagemController {

	//private Logger logger = LoggerFactory.getLogger(BloqueioController.class);

	@Autowired
	public CartaoRepository cartaoRepository;
	@Autowired
	public RelacionaCartaoClient cartaoClient;
	@Autowired
	public ViagemRepository viagemRepository;

	@PostMapping("/{id}")
	public ResponseEntity<?> avisarViagem(@PathVariable @NotNull Long id, @RequestHeader("user-agent") String userAgent,
			@RequestHeader("host") String ipHost, UriComponentsBuilder uriBuilder,
			@RequestBody @Valid @NotNull ViagemRequest request) {

		Optional<Cartao> procuraCartao = cartaoRepository.findById(id);
		if (procuraCartao.isPresent()) {
			Cartao cartao = procuraCartao.get();

			if (request.dataMaiorQueHoje(request.getDataTermino()) == true) {
				try {
					cartaoClient.retornaStatusAviso(cartao.getNumero(), request);
					Viagem viagem = request.toModel(procuraCartao.get(), ipHost, userAgent);
					viagemRepository.save(viagem);

				} catch (Exception e) {
					return ResponseEntity.badRequest().build();
				}

			} else return ResponseEntity.badRequest().build();

			URI uri = uriBuilder.path("/avisos/{id}").buildAndExpand(procuraCartao.get().getId()).toUri();
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
