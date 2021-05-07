package br.com.zupacademy.proposta.novaproposta;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import br.com.zupacademy.proposta.annotations.VerificaCpfCnpj;

public class PropostaRequest {

	@NotBlank
	@VerificaCpfCnpj
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

	public PropostaRequest(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotBlank String endereco, @Positive @NotNull BigDecimal salario) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Proposta converterToModel() {
		return new Proposta(this.documento, this.email, this.nome, this.endereco, this.salario);
	}

	
}
