package com.model;

import java.util.Set;

public class OdontologoDTO {
	
		private Long id;
		private Integer numeroMatricula;
		private String nombre;
		private String apellido;
		
		private Set<PacienteDTO> listaDePacientes;
	
	
	public OdontologoDTO() {
	}
	
	public OdontologoDTO(Long id, Integer numeroMatricula, String nombre, String apellido) {
			this.id = id;
			this.numeroMatricula = numeroMatricula;
			this.nombre = nombre;
			this.apellido = apellido;
		}
		
		public Long getId() {
			return id;
		}
		
		public void setId(Long id) {
			this.id = id;
		}
	
	@Override
	public String toString() {
		return "OdontologoDTO{" +
				"id=" + id +
				", numeroMatricula=" + numeroMatricula +
				", nombre='" + nombre + '\'' +
				", apellido='" + apellido + '\'' +
				'}';
	}
}
