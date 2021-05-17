package br.com.zupacademy.proposta.novabiometria;

import br.com.zupacademy.proposta.feign.cartao.Cartao;

import java.util.Base64;

public class BiometriaRequest {
    public String fingerPrint;

    @Deprecated
    public BiometriaRequest() {
    }

    public BiometriaRequest(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public String converteParaBase64() {
        return Base64.getEncoder().encodeToString(fingerPrint.getBytes());
    }

    public Biometria toModel(Cartao cartao) {
        return new Biometria(cartao, converteParaBase64());
    }
}
