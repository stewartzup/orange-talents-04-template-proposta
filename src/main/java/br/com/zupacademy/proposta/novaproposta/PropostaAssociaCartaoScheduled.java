package br.com.zupacademy.proposta.novaproposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zupacademy.proposta.feign.cartao.Cartao;
import br.com.zupacademy.proposta.feign.cartao.CartaoRepository;
import br.com.zupacademy.proposta.feign.cartao.NumeroCartaoResponse;
import br.com.zupacademy.proposta.feign.cartao.RelacionaCartaoClient;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@EnableScheduling
public class PropostaAssociaCartaoScheduled {
	
	private Logger logger = LoggerFactory.getLogger(PropostaAssociaCartaoScheduled.class);
	
	@Autowired
	private RelacionaCartaoClient relacionaCartao;
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private CartaoRepository cartaoRepository;

	@Scheduled(cron = "*/30 * * * * *")
	public void buscaCartao() {
		List<Proposta> propostaElegivel = propostaRepository.buscaPropostasElegiveisSemCartao();
		logger.info("entrei no log");
		for (Proposta proposta : propostaElegivel) {
			try {
				NumeroCartaoResponse dadosCartao = relacionaCartao.getCartao(proposta.getId());
				if(dadosCartao.getId() == proposta.getNumeroCartao() ) {
					logger.info("Proposta de id:{} recebeu o numero de cartao:{}", proposta.getId(), dadosCartao.getId());
					logger.info("test");
					//proposta.setNumeroCartao(dadosCartao.getId());
					Cartao cartao = dadosCartao.toModel(proposta);
					proposta.associaCartao(cartao);
					cartaoRepository.save(cartao);
					propostaRepository.save(proposta);
					
				}
				
	
				//proposta.setCartao(dadosCartao.toModel(proposta));
				
			} catch (Exception e) {
				logger.info("{}", e.getMessage());
				logger.info("A proposta de id:{} ainda não recebeu o numero de cartao", proposta.getId());
			}
		}
		
	}

}
