package br.com.zupacademy.proposta.carteira;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import br.com.zupacademy.proposta.feign.cartao.Cartao;

@Entity
public class Carteira {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(cascade = { CascadeType.ALL })
	public Cartao cartao;
	@Email
	@NotBlank
	private String email;
	@Enumerated(EnumType.STRING)
	public CarteiraOpcao carteiraOpcao = CarteiraOpcao.EMPYT;

	public Carteira(Cartao cartao, @Email @NotBlank String email, CarteiraOpcao carteiraOpcao) {
		this.cartao = cartao;
		this.email = email;
		this.carteiraOpcao = carteiraOpcao;
	}

	@Deprecated
	public Carteira() {

	}

	public Long getId() {
		return id;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public String getEmail() {
		return email;
	}

	public CarteiraOpcao getCarteiraOpcao() {
		return carteiraOpcao;
	}

	public void setCarteiraOpcao(CarteiraOpcao carteiraOpcao) {
		this.carteiraOpcao = carteiraOpcao;
	}

}
