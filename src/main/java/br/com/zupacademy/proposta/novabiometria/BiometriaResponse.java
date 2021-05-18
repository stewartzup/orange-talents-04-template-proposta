package br.com.zupacademy.proposta.novabiometria;

import br.com.zupacademy.proposta.feign.cartao.Cartao;

public class BiometriaResponse {
	public Long id;
	public Cartao cartao;
	public String fingerPrint;

	public BiometriaResponse(Biometria biometria) {
		super();
		this.id = biometria.id;
		this.cartao = biometria.cartao;
		this.fingerPrint = biometria.fingerPrint;
	}

	@Deprecated
	public BiometriaResponse() {

	}

	public Long getId() {
		return id;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public String getFingerPrint() {
		return fingerPrint;
	}

}
