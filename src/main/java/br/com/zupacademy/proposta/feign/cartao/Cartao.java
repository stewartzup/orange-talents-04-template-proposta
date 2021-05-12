package br.com.zupacademy.proposta.feign.cartao;

import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;

//@Entity
public class Cartao {

	public String id;
	public DateTimeFormat data;
	public String titular;
	
}
