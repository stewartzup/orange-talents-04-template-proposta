package br.com.zupacademy.proposta.feign.restricao;

public class AnaliseRestricaoResponse {

	private String documento;
	private String nome;
	private String idProposta;
	private ResultadoSolicitacao resultadoSolicitacao;

	public AnaliseRestricaoResponse(String documento, String nome, String idProposta,
			ResultadoSolicitacao resultadoSolicitacao) {
		super();
		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
		this.resultadoSolicitacao = resultadoSolicitacao;
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

	public ResultadoSolicitacao getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	@Override
	public String toString() {
		return "AnaliseRestricaoResponse [documento=" + documento + ", nome=" + nome + ", idProposta=" + idProposta
				+ ", resultadoSolicitacao=" + resultadoSolicitacao + "]";
	}

}
