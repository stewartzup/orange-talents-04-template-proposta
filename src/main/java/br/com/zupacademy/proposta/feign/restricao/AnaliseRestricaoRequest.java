package br.com.zupacademy.proposta.feign.restricao;

import br.com.zupacademy.proposta.novaproposta.Proposta;

public class AnaliseRestricaoRequest {

	private String documento;
	private String nome;
	private String idProposta;

	public AnaliseRestricaoRequest(Proposta proposta) {
		super();
		this.documento = proposta.documento;
		this.nome = proposta.nome;
		this.idProposta = proposta.getId().toString();
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getIdProposta() {
		return idProposta;
	}

}
