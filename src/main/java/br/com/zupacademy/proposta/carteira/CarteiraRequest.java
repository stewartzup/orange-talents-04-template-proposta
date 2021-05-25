package br.com.zupacademy.proposta.carteira;

import javax.validation.constraints.Email;

import br.com.zupacademy.proposta.feign.cartao.Cartao;

public class CarteiraRequest {
	@Email
	private String email;
	private CarteiraOpcao carteira;
	
	public CarteiraRequest(String email, CarteiraOpcao carteira) {
		super();
		this.email = email;
		this.carteira = carteira;
	}
	@Deprecated
	public CarteiraRequest() {
		
	}
	
	public String getEmail() {
		return email;
	}

	public CarteiraOpcao getCarteira() {
		return carteira;
	}

	public Carteira toModel(Cartao cartao) {
		return new Carteira(cartao, this.email,this.carteira);
	}
	
}
