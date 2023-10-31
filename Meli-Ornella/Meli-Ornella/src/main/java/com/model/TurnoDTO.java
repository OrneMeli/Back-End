package com.model;

import java.time.LocalDate;

public class TurnoDTO {
	private Long id;
	private PacienteDTO pacienteDTO;
	private OdontologoDTO odontologoDTO;
	private LocalDate date;
	
	public TurnoDTO() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "TurnoDTO{" +
				"id=" + id +
				", paciente=" + pacienteDTO +
				", odontologo=" + odontologoDTO +
				", date=" + date +
				'}';
	}
}
