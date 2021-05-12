package br.com.zupacademy.proposta.novaproposta;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import br.com.zupacademy.proposta.annotations.VerificaCpfCnpj;

public class PropostaRequest {

	@NotBlank
	@VerificaCpfCnpj
	@Column(unique=true)
	public String documento;
	@NotBlank
	@Email
	public String email;
	@NotBlank
	public String nome;
	@NotBlank
	public String endereco;
	@NotNull
	@Positive
	public BigDecimal salario;
	@Enumerated(EnumType.STRING)
	public StatusProposta status;
	

	public PropostaRequest(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull @Positive BigDecimal salario, StatusProposta status) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
		this.status = status;
	}

	public Proposta converterToModel() {
		return new Proposta(this.documento, this.email, this.nome, this.endereco, this.salario, this.status);
	}

}
