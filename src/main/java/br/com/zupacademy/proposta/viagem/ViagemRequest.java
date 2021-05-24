package br.com.zupacademy.proposta.viagem;

import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.proposta.feign.cartao.Cartao;

public class ViagemRequest {

	@NotNull @NotEmpty
	private String destinoViagem;
	@NotNull
	private LocalDate dataTermino;

	public ViagemRequest(@NotNull @NotEmpty String destinoViagem, @NotNull LocalDate dataTermino) {
	
		this.destinoViagem = destinoViagem;
		this.dataTermino = dataTermino;
	}

	public String getDestino() {
		return destinoViagem;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	@Override
	public String toString() {
		return "ViagemRequest [destino=" + destinoViagem + ", dataTermino=" + dataTermino + "]";
	}

	public Viagem toModel(Cartao cartao, String ipCliente, String agenteUsuario) {
		return new Viagem(this.destinoViagem, this.dataTermino, ipCliente, agenteUsuario, cartao);

	}

	public boolean dataMaiorQueHoje(LocalDate dataTermino) {
		LocalDate hoje = LocalDate.now();
		return dataTermino.compareTo(hoje) >= 0;
	}
}
