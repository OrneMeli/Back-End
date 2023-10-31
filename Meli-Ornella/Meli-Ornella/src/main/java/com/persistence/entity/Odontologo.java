package com.persistence.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Odontologos")
public class Odontologo {
	
	@Id
	@SequenceGenerator(name = "odontologo_sequence", sequenceName = "odontologo_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odontologo_sequence")
		private Long id;
		private Integer numeroMatricula;
		private String nombre;
		private String apellido;
		
	@OneToMany(mappedBy = "odontologo")
	@JsonIgnore
		private Set<Turno> listaDeTurnos;
	
	public void addTurnoToListaDeTurnos(Turno turno) {
		listaDeTurnos.add(turno);
	}
	
	public void deleteTurnoFromListaDeTurnos(Turno turno) {
		listaDeTurnos.remove(turno);
	}
	// private List<Turno> turnos
		public Odontologo(Long id, Integer numeroMatricula, String nombre, String apellido) {
			this.id = id;
			this.numeroMatricula = numeroMatricula;
			this.nombre = nombre;
			this.apellido = apellido;
		}
	
	public Odontologo() {
	}
	
	public Odontologo(Integer numeroMatricula, String nombre, String apellido) {
		this.numeroMatricula = numeroMatricula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.listaDeTurnos = new HashSet<>();
	}
	
	public Long getId() {
			return id;
		}
		
		public void setId(Long id) {
			this.id = id;
		}
	
	@Override
	public String toString() {
		return "Odontologo{" +
				"id=" + id +
				", numeroMatricula=" + numeroMatricula +
				", nombre='" + nombre + '\'' +
				", apellido='" + apellido + '\'' +
				'}';
	}
}
