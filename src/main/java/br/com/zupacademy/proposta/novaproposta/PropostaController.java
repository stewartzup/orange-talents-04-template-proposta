package br.com.zupacademy.proposta.novaproposta;


import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;



@RestController
@RequestMapping("/propostas")
public class PropostaController {
	

	@Autowired
	public PropostaRepository propostaRepository;
	
	@PostMapping
	public ResponseEntity<PropostaResponse> cadastrarAutor(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder uriBuilder) {
		Proposta proposta = request.converterToModel();
		propostaRepository.save(proposta);
		URI uri = uriBuilder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(uri).body(new PropostaResponse(proposta));
				
			
		
	}

}
