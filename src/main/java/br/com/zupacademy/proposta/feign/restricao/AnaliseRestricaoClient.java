package br.com.zupacademy.proposta.feign.restricao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(value = "solicitacao", url = "${financeiro.dominio}")
public interface AnaliseRestricaoClient {

	  @PostMapping
	  AnaliseRestricaoResponse analisaRestricao(AnaliseRestricaoRequest request);
	}