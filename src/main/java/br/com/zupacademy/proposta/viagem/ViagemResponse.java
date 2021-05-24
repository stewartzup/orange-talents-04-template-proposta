package br.com.zupacademy.proposta.viagem;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ViagemResponse {
	private String destino;
    private LocalDateTime criadoEm;
    private LocalDate dataTermino;
    
	public ViagemResponse(String destino, LocalDateTime criadoEm, LocalDate dataTermino) {
		super();
		this.destino = destino;
		this.criadoEm = criadoEm;
		this.dataTermino = dataTermino;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}
    
    

}
