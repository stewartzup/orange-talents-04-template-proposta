package br.com.zupacademy.proposta.feign.cartao.bloqueiocartao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BloqueioRequest {
	@JsonProperty
	private String sistemaResponsavel;

	@Deprecated
	public BloqueioRequest() {
		
	}
	
	public BloqueioRequest(String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

	public void setSistemaResponsavel(String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}
	 

	

}
