package br.com.zupacademy.proposta.novaproposta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PropostaRepository extends JpaRepository<Proposta, Long>{

	Optional<Proposta> findByDocumento(String documento);
}
