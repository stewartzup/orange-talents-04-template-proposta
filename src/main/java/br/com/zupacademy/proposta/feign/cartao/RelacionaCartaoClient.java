package br.com.zupacademy.proposta.feign.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zupacademy.proposta.feign.cartao.bloqueiocartao.BloqueioRequest;
import br.com.zupacademy.proposta.feign.cartao.bloqueiocartao.BloqueioResponse;

@FeignClient(name = "",value = "cartoes", url = "${cartao.dominio}")
public interface RelacionaCartaoClient {

	@GetMapping //(value = "/api/cartoes")
	NumeroCartaoResponse getCartao(@RequestParam("idProposta") Long id);

	@RequestMapping(method = RequestMethod.POST,value = "/{id}/bloqueios", produces  = "application/json")
    public BloqueioResponse retornaSituacaoCartaoBloqueado(@PathVariable("id") String id,@RequestBody BloqueioRequest bloqueioRequest);
}
