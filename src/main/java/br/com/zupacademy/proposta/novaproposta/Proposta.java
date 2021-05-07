package br.com.zupacademy.proposta.novaproposta;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.br.CPF;

import br.com.zupacademy.proposta.annotations.VerificaCpfCnpj;

@Entity
public class Proposta {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	public Long id;
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
	//@NotBlank
	@Positive
	public BigDecimal salario;
	
	@Deprecated
	public Proposta() {
	}
	
	public Proposta(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotBlank String endereco, @Positive BigDecimal salario) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco= endereco;
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}
	

}
