package br.com.zupacademy.proposta.novabiometria;

import java.util.Base64;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class VerificaBase64Valida implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return BiometriaRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		if (errors.hasErrors())
			return;

		BiometriaRequest biometriaRequest = (BiometriaRequest) target;

		Base64.Decoder decoder = Base64.getDecoder();

		try {

			decoder.decode(biometriaRequest.getFingerPrint());

		} catch (IllegalArgumentException e) {
			errors.reject("Nao eh uma base64 valida");

		}

	}

}
