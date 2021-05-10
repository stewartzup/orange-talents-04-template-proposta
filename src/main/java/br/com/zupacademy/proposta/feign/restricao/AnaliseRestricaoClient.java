package br.com.zupacademy.proposta.feign.restricao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(value = "solicitacao", url = "http://localhost:9999/api/solicitacao")
public interface AnaliseRestricaoClient {

	  @PostMapping
	  AnaliseRestricaoResponse analisaRestricao(AnaliseRestricaoRequest request);
	}