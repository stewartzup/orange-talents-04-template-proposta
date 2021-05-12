package br.com.zupacademy.proposta.novaproposta;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

	Optional<Proposta> findByDocumento(String documento);

	@Query("SELECT p from Proposta p WHERE p.status = 'ELEGIVEL' AND p.numeroCartao = NULL")
	public java.util.List<Proposta> buscaPropostasElegiveisSemCartao();
}
