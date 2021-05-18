package br.com.zupacademy.proposta.feign.cartao;

import br.com.zupacademy.proposta.novaproposta.Proposta;

public class DadosCartaoRequest {
	
	private String idProposta;
	
	

	public DadosCartaoRequest(Proposta proposta) {
		this.idProposta = proposta.getId().toString();
	}

	public String getIdProposta() {
		return idProposta;
	}
	
	

}
