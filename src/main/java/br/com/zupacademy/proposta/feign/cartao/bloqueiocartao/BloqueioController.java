package br.com.zupacademy.proposta.feign.cartao.bloqueiocartao;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.zupacademy.proposta.feign.cartao.Cartao;
import br.com.zupacademy.proposta.feign.cartao.CartaoRepository;
import br.com.zupacademy.proposta.feign.cartao.RelacionaCartaoClient;
import br.com.zupacademy.proposta.feign.cartao.StatusCartaoBloqueio;
import feign.FeignException;

@RestController
@RequestMapping("/bloqueios")
public class BloqueioController {

	private Logger logger = LoggerFactory.getLogger(BloqueioController.class);
	@Autowired
	public CartaoRepository cartaoRepository;
	@Autowired
	public RelacionaCartaoClient cartaoClient;
	@Autowired
	public BloqueioRepository bloqueioRepository;

	@PostMapping("/bloquear/{id}")
	public ResponseEntity<?> bloquearCartao(@PathVariable @NotNull Long id,
			@RequestHeader("user-agent") String userAgent, @RequestHeader("host") String ipHost,
			UriComponentsBuilder uriBuilder) {

		Optional<Cartao> procuraCartao = cartaoRepository.findById(id);
		if (procuraCartao.isPresent()) {
			Cartao cartao = procuraCartao.get();

			try {
				cartaoClient.retornaSituacaoCartaoBloqueado(cartao.getNumero(), new BloqueioRequest());

				Bloqueio bloqueio = new Bloqueio(procuraCartao.get(), ipHost, userAgent);
				bloqueioRepository.save(bloqueio);
				logger.info("{} {} {}", bloqueio.getCartao().numero, userAgent, bloqueio.getIpCliente());
				cartao.setStatusCartaoBloqueio(StatusCartaoBloqueio.BLOQUEADO);
				cartaoRepository.save(cartao);

			} catch (FeignException e) {
				// e.printStackTrace();
				if (e.status() == 422) {
					return ResponseEntity.unprocessableEntity().build();
					// throw e;
				}
			}
			URI uri = uriBuilder.path("/bloqueio/{id}").buildAndExpand(procuraCartao.get().getId()).toUri();
			return ResponseEntity.ok().build();
//			if(bloqueio.getCartao().getStatusCartaoBloqueio() == StatusCartaoBloqueio.ATIVO ) {
//				logger.info("Status do cartao eh {}", bloqueio.getCartao().getStatusCartaoBloqueio());
//				bloqueio.getCartao().setStatusCartaoBloqueio(StatusCartaoBloqueio.BLOQUEADO);
//				return ResponseEntity.created();
//			}

			// return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.notFound().build();
	}

}
