package com.persistence.repository;

import com.persistence.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Set;

@Repository
public interface TurnoRepositorio extends JpaRepository<Turno, Long> {
	
	@Query(value = "SELECT * FROM Turnos WHERE paciente_id = ?1", nativeQuery = true)
	Set<Turno> buscarTurnosPorPaciente(Long id);
	
	@Query(value = "SELECT * FROM Turnos WHERE date = ?1", nativeQuery = true)
	Set<Turno> buscarTurnosPorFecha(Date date);
	
}
