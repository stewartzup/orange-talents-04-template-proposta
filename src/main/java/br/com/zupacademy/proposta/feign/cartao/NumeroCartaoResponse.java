package br.com.zupacademy.proposta.feign.cartao;


import br.com.zupacademy.proposta.novaproposta.Proposta;

public class NumeroCartaoResponse {
	    public String id;

	    public String getId() {
	        return id;
	    }
	    

	    public NumeroCartaoResponse(String id) {
			super();
			this.id = id;
		}


		public Cartao toModel(Proposta proposta) {
	        return new Cartao(proposta, id);
	    }
	   
		
	}
