package br.com.zupacademy.proposta.feign.cartao.bloqueiocartao;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.zupacademy.proposta.feign.cartao.Cartao;

@Entity
public class Bloqueio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(cascade = { CascadeType.ALL })
	public Cartao cartao;
	private String ipCliente;
	private String agentUser;
	private LocalDateTime instanteBloqueio = LocalDateTime.now();

	public Bloqueio(Cartao cartao,String ipCliente, String agentUser) {
		this.cartao = cartao;
		this.ipCliente = ipCliente;
		this.agentUser = agentUser;
	}

	@Deprecated
	public Bloqueio() {

	}

	public Long getId() {
		return id;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public String getIpCliente() {
		return ipCliente;
	}

	public String getAgentUser() {
		return agentUser;
	}

	public LocalDateTime getInstanteBloqueio() {
		return instanteBloqueio;
	}
	
}
