package com.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Domicilio {
	
	@Id
	@SequenceGenerator(name = "domicilio_sequence", sequenceName = "domicilio_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "domicilio_sequence")
		private Integer id;
		private String calle;
		private String numero;
		private String localidad;
		private String provincia;
		
		public Domicilio() {
		}
		
		public Domicilio(Integer id, String calle, String numero, String localidad, String provincia) {
			this.id = id;
			this.calle = calle;
			this.numero = numero;
			this.localidad = localidad;
			this.provincia = provincia;
		}
		public Domicilio(String calle, String numero, String localidad, String provincia) {
			this.calle = calle;
			this.numero = numero;
			this.localidad = localidad;
			this.provincia = provincia;
		}
		
		public Integer getId() {
			return id;
		}
		
		public void setId(Integer id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return "Domicilio{" +
					"id=" + id +
					", calle='" + calle + '\'' +
					", numero='" + numero + '\'' +
					", localidad='" + localidad + '\'' +
					", provincia='" + provincia + '\'' +
					'}';
		}
	

}
