package com.service.implementation;

import com.config.exception.ResourceNotFoundException;
import com.model.PacienteDTO;

import java.util.Set;

public interface IPacienteService {
	
	void crearPaciente(PacienteDTO pacienteDTO);
	Set<PacienteDTO> listarPacientes();
	PacienteDTO buscarPacientePorID(Long id) throws ResourceNotFoundException;
	void modificarPaciente(PacienteDTO pacienteDTO) throws ResourceNotFoundException;
	void eliminarPaciente(Long id) throws ResourceNotFoundException;
	
}
