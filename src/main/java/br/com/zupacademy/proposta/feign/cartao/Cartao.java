package br.com.zupacademy.proposta.feign.cartao;

import javax.persistence.*;
import br.com.zupacademy.proposta.novaproposta.Proposta;
import br.com.zupacademy.proposta.novaproposta.StatusProposta;

@Entity
public class Cartao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String numero;
	@Enumerated(EnumType.STRING)
	public StatusCartaoBloqueio statusCartaoBloqueio = StatusCartaoBloqueio.ATIVO;

	@Deprecated
	public Cartao() {
	}

	public Cartao(String numero) {
		this.numero = numero;
	}

	public String getNumero() {
		return numero;
	}

	public Long getId() {
		return id;
	}

	public StatusCartaoBloqueio getStatusCartaoBloqueio() {
		return statusCartaoBloqueio;
	}

	public void setStatusCartaoBloqueio(StatusCartaoBloqueio statusCartaoBloqueio) {
		this.statusCartaoBloqueio = statusCartaoBloqueio;
	}
	

}
