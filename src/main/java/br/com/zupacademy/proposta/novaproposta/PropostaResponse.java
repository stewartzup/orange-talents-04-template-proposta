package br.com.zupacademy.proposta.novaproposta;

import java.math.BigDecimal;


public class PropostaResponse {

	public String documento;
	public String email;
	public String nome;
	public String endereco;
	public BigDecimal salario;
	
	public PropostaResponse(Proposta proposta) {
		this.documento = proposta.getDocumento();
		this.email = proposta.getEmail();
		this.nome = proposta.getNome();
		this.endereco = proposta.getEndereco();
		this.salario = proposta.getSalario();
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
	
	
}
