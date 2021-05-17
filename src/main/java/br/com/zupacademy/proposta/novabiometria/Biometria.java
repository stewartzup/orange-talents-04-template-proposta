package br.com.zupacademy.proposta.novabiometria;

import br.com.zupacademy.proposta.feign.cartao.Cartao;



import javax.persistence.*;




//@Entity
public class Biometria {

		@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		public Long id;
		@ManyToOne
		public Cartao cartao;
		public String fingerPrint;

		public Biometria(Cartao cartao, String fingerPrint) {
			this.cartao = cartao;
			this.fingerPrint = fingerPrint;
		}

		@Override
		public String toString() {
			return "Biometria{" +
					"id=" + id +
					", cartao=" + cartao +
					", fingerPrint='" + fingerPrint + '\'' +
					'}';
		}
	}