package br.com.zupacademy.proposta.feign.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cartoes", url = "${cartao.dominio}")
public interface RelacionaCartaoClient {

	@GetMapping //(value = "/api/cartoes")
	NumeroCartaoResponse getCartao(@RequestParam("idProposta") Long id);

}
