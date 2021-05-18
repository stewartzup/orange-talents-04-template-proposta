package br.com.zupacademy.proposta.novabiometria;

import br.com.zupacademy.proposta.feign.cartao.Cartao;

import java.util.Base64;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BiometriaRequest {
    public String fingerPrint;

    @Deprecated
    public BiometriaRequest() {
    }
    
    public BiometriaRequest(@JsonProperty String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public Biometria toModel(Cartao cartao) {
        return new Biometria(cartao, fingerPrint);
    }
    
    public static String decodeURL(String fingerPrint) {

        byte[] decodedBytes = Base64.getUrlDecoder().decode(fingerPrint);
        return new String(decodedBytes);

    }
}
