package com.service.implementation;

import com.config.exception.ResourceNotFoundException;
import com.model.OdontologoDTO;

import java.util.Set;

public interface IOdontologoService {
	
	void crearOdontologo(OdontologoDTO odontologoDTO);
	Set<OdontologoDTO> listarOdontologos();
	OdontologoDTO buscarOdontologoPorID(Long id) throws ResourceNotFoundException;
	void modificarOdontologo(OdontologoDTO odontologoDTO) throws ResourceNotFoundException;
	void eliminarOdontologo(Long id) throws ResourceNotFoundException;

}
