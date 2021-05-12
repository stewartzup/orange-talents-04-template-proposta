package br.com.zupacademy.proposta.feign.cartao;

public class NumeroCartaoResponse {
	public String id;

	
	@Deprecated
	public NumeroCartaoResponse() {
	
	}

	public NumeroCartaoResponse(String id) {
		super();
		this.id = id;

	}

	public String getId() {
		return id;
	}

}
