package com.model;

import java.util.Date;
import java.util.Set;

public class PacienteDTO {
	
	private Long id;
	private String dni;
	private String nombre;
	private String apellido;
	private Date fechaIngreso;
	private Set<OdontologoDTO> listaOdontologos;
	private DomicilioDTO domicilioDTO;

	public PacienteDTO() {
	}

	public PacienteDTO(Long id, String dni, String nombre, String apellido, Date fechaIngreso, DomicilioDTO domicilioDTO) {
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaIngreso = fechaIngreso;
		this.domicilioDTO = domicilioDTO;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public DomicilioDTO getDomicilio() {
		return domicilioDTO;
	}
	
	@Override
	public String toString() {
		return "PacienteDTO{" +
				"id=" + id +
				", nombre='" + nombre + '\'' +
				", apellido='" + apellido + '\'' +
				", dni='" + dni + '\'' +
				", fechaIngreso=" + fechaIngreso +
				", domicilio=" + domicilioDTO +
				'}';
	}
	
}
