package br.com.zupacademy.proposta.feign.cartao;

import javax.persistence.*;
import br.com.zupacademy.proposta.novaproposta.Proposta;

@Entity
public class Cartao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String numero;

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

}
