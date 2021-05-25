package br.com.zupacademy.proposta.carteira;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.zupacademy.proposta.feign.cartao.Cartao;
import br.com.zupacademy.proposta.feign.cartao.CartaoRepository;
import br.com.zupacademy.proposta.feign.cartao.RelacionaCartaoClient;
import br.com.zupacademy.proposta.novaproposta.PropostaResponse;
import br.com.zupacademy.proposta.novaproposta.StatusProposta;
import br.com.zupacademy.proposta.viagem.ViagemRequest;
import feign.FeignException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/carteira")
public class CarteiraController {

	private Logger logger = LoggerFactory.getLogger(CarteiraController.class);
	@Autowired
	private CartaoRepository cartaoRepository;
	@Autowired
	public RelacionaCartaoClient cartaoClient;
	@Autowired
	public CarteiraRepository carteiraRepository;

	@PostMapping("/{id}")
	public ResponseEntity<?> associarCarteira(@PathVariable @NotNull Long id, UriComponentsBuilder uriBuilder,
			@RequestBody @Valid CarteiraRequest request) {

		Optional<Cartao> procuraCartao = cartaoRepository.findById(id);
		Optional<Carteira> procuraCarteira = carteiraRepository.findById(id);
		Cartao cartao = procuraCartao.get();

		try {
			cartaoClient.retornaSituacaoCarteira(cartao.getNumero(), request);
		} catch (FeignException e) {
			if (e.status() == 422)
				return ResponseEntity.unprocessableEntity().build();
			else
				throw e;
		}

		Carteira carteira = request.toModel(cartao);
		if (procuraCartao.isPresent()) {
			carteiraRepository.save(carteira);
			try {
				//carteira.setCarteiraOpcao(request.getCarteira());
				carteiraRepository.save(carteira);

			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}
			URI uri = uriBuilder.path("/carteira/{id}").buildAndExpand(procuraCartao.get().getId()).toUri();
			return ResponseEntity.created(uri).build();
		}

		return ResponseEntity.notFound().build();

	}
}