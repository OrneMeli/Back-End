package com.model;


public class DomicilioDTO {
	private Integer id;
		private String calle;
		private String numero;
		private String localidad;
		private String provincia;
		public DomicilioDTO() {
		}
		
		public DomicilioDTO(Integer id, String calle, String numero, String localidad, String provincia) {
			this.id = id;
			this.calle = calle;
			this.numero = numero;
			this.localidad = localidad;
			this.provincia = provincia;
		}
		public DomicilioDTO(String calle, String numero, String localidad, String provincia) {
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
			return "DomicilioDTO{" +
					"id=" + id +
					", calle='" + calle + '\'' +
					", numero='" + numero + '\'' +
					", localidad='" + localidad + '\'' +
					", provincia='" + provincia + '\'' +
					'}';
		}
	

}
