package br.com.zupacademy.proposta.viagem;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.proposta.feign.cartao.Cartao;

@Entity
public class Viagem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@NotNull
	private String destinoViagem;
	private LocalDate dataTermino;
	private LocalDateTime instante = LocalDateTime.now();
	private String ipCliente;
	private String agenteDoUsuario;
	@ManyToOne(cascade = { CascadeType.ALL })
	private Cartao cartao;
	
	@Deprecated
	public Viagem() {}

	public Viagem(@NotNull String destinoViagem, LocalDate dataTermino, String ipCliente, String agenteDoUsuario,
			Cartao cartao) {
		super();
		this.destinoViagem = destinoViagem;
		this.dataTermino = dataTermino;
		this.ipCliente = ipCliente;
		this.agenteDoUsuario = agenteDoUsuario;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public String getDestinoViagem() {
		return destinoViagem;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	public LocalDateTime getInstante() {
		return instante;
	}

	public String getIpCliente() {
		return ipCliente;
	}

	public String getAgenteDoUsuario() {
		return agenteDoUsuario;
	}

	public Cartao getCartao() {
		return cartao;
	}
	
	
	
	
}


