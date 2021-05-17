package br.com.zupacademy.proposta.novaproposta;

import java.math.BigDecimal;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.zupacademy.proposta.feign.cartao.Cartao;


public class PropostaResponse {

	public String documento;
	public String email;
	public String nome;
	public String endereco;
	public BigDecimal salario;
	@Enumerated(EnumType.STRING)
	public StatusProposta status;
	 private Cartao cartao;
	
	public PropostaResponse(Proposta proposta) {
		this.documento = proposta.getDocumento();
		this.email = proposta.getEmail();
		this.nome = proposta.getNome();
		this.endereco = proposta.getEndereco();
		this.salario = proposta.getSalario();
		this.status = proposta.getStatus();
		 this.cartao = proposta.getCartao();
		
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public StatusProposta getStatus() {
		return status;
	}
	public Cartao getCartao() {
        return cartao;
    }
	
	
	
	
}
