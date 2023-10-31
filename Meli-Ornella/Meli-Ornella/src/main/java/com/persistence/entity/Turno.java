package com.persistence.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Turnos")
public class Turno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "odontologo_id")
	private Odontologo odontologo;
	private Date date;
	
	public Turno() {
	}
	
	public Turno(Paciente paciente, Odontologo odontologo, Date date) {
		this.paciente = paciente;
		this.odontologo = odontologo;
		this.date = date;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Paciente getPaciente() {
		return paciente;
	}
	
	public Odontologo getOdontologo() {
		return odontologo;
	}
	
	@Override
	public String toString() {
		return "Turno{" +
				"id=" + id +
				", paciente=" + paciente +
				", odontologo=" + odontologo +
				", date=" + date +
				'}';
	}
}
