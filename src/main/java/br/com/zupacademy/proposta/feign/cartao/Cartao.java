package br.com.zupacademy.proposta.feign.cartao;

import javax.persistence.*;
import br.com.zupacademy.proposta.novaproposta.Proposta;

@Entity
@Table(name = "cartao")
public class Cartao {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	@ManyToOne
	public Proposta proposta;
	public String numero;

	@Deprecated
	public Cartao() {
	}

	public Cartao( Proposta proposta, String numero) {
		this.proposta = proposta;
		this.numero = numero;
	}
	public String getNumero() {
		return numero;
	}
}
