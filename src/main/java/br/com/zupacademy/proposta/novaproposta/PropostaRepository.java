package br.com.zupacademy.proposta.novaproposta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

	Optional<Proposta> findByDocumento(String documento);

	@Query("SELECT p FROM Proposta p WHERE p.status = 'ELEGIVEL' AND p.cartao.id = NULL")
	List<Proposta> buscaPropostasElegiveisSemCartao();
}
