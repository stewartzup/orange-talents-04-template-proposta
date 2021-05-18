package br.com.zupacademy.proposta.feign.cartao;


import br.com.zupacademy.proposta.novaproposta.Proposta;

public class NumeroCartaoResponse {
	    public String id;

	    public NumeroCartaoResponse(String id) {
			super();
			this.id = id;
		}
	    @Deprecated
	    public NumeroCartaoResponse() {
	    	
	    }

	    public String getId() {
			return id;
		}	   
		
	}

		
	   
		
	
