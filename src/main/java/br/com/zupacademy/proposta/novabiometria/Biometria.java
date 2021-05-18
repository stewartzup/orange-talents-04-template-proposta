package br.com.zupacademy.proposta.novabiometria;

import br.com.zupacademy.proposta.feign.cartao.Cartao;

import java.time.LocalDateTime;
import java.util.Map;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Biometria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	private LocalDateTime dataCriacao = LocalDateTime.now();
	@ManyToOne(cascade = {CascadeType.ALL})
	public Cartao cartao;
	@NotNull
	public String fingerPrint;
	

	public Biometria(Cartao cartao, @NotNull String fingerPrint) {
		this.cartao = cartao;
		this.fingerPrint = fingerPrint;
	}

	@Override
	public String toString() {
		return "Biometria{" + "id=" + id + ", cartao=" + cartao + ", fingerPrint='" + fingerPrint + '\'' + '}';
	}

	public Long getId() {
		return id;
	}

	

	
}