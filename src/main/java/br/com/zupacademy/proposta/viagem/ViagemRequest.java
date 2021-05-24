package br.com.zupacademy.proposta.viagem;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import br.com.zupacademy.proposta.feign.cartao.Cartao;

public class ViagemRequest {

	@NotNull
	private String destino;
	@NotNull
	private LocalDate dataTermino;

	public ViagemRequest(@NotNull String destino, @NotNull LocalDate dataTermino) {
		super();
		this.destino = destino;
		this.dataTermino = dataTermino;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	@Override
	public String toString() {
		return "ViagemRequest [destino=" + destino + ", dataTermino=" + dataTermino + "]";
	}
	
	 public Viagem toModel(Cartao cartao, String ipCliente, String agenteUsuario) {
	             return new Viagem(
	                this.destino,
	                this.dataTermino,
	                ipCliente,
	                agenteUsuario,
	                cartao          
	        );

}
	 }
