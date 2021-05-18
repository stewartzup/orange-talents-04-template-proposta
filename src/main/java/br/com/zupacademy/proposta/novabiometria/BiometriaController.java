package br.com.zupacademy.proposta.novabiometria;

import java.net.URI;
import java.util.Iterator;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.zupacademy.proposta.feign.cartao.Cartao;
import br.com.zupacademy.proposta.feign.cartao.CartaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class BiometriaController {

	private Logger logger = LoggerFactory.getLogger(BiometriaController.class);

	@Autowired
	public BiometriaRepository biometriaRepository;

	@Autowired
	public CartaoRepository cartaoRepository;

	@InitBinder
	public void iniciaValidador64(WebDataBinder binder) {
		binder.addValidators(new VerificaBase64Valida());

	}

	@PostMapping("/{id}")
	@Transactional
	ResponseEntity<BiometriaResponse> cadastrarBiometria(@PathVariable("id") Long id,
			@RequestBody @Valid BiometriaRequest request, UriComponentsBuilder uriBuilder) {

		Optional<Cartao> procuraCartao = cartaoRepository.findById(id);

		logger.info("a entrar no if");
		if (procuraCartao.isPresent()) {
			Biometria biometria = request.toModel(procuraCartao.get());

			logger.info("Quem irá receber o fingerPrint eh o Cartao de Id: ${}  e NumeroCartao: ${}",
					procuraCartao.get().id, procuraCartao.get().numero);
//			if(biometria.fingerPrint.equals(request.fingerPrint)){
//			return ResponseEntity.badRequest().build();
//			}
			biometriaRepository.save(biometria);

			URI uri = uriBuilder.path("/{id}").buildAndExpand(biometria.getId()).toUri();
			return ResponseEntity.created(uri).build();
		} else {
			logger.info("nao passou");
			return ResponseEntity.notFound().build();
		}

	}
}
