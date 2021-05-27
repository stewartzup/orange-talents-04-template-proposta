package br.com.zupacademy.proposta.annotations;

import javax.persistence.AttributeConverter;

import org.springframework.security.crypto.encrypt.Encryptors;

public class CriptografaDocumento implements AttributeConverter<String, String> {

	@Override
	public String convertToDatabaseColumn(String attribute) {
		// TODO Auto-generated method stub
		return Encryptors.text("${criptografia.password}", "7C8EF022EEC320E0").encrypt(attribute);
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		// TODO Auto-generated method stub
		return Encryptors.text("${criptografia.password}", "7C8EF022EEC320E0").decrypt(dbData);
	}
	
	

}
