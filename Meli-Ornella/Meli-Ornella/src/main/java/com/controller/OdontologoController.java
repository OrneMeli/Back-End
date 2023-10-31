package com.controller;

import com.config.exception.BadRequestException;
import com.config.exception.ResourceNotFoundException;
import com.model.OdontologoDTO;
import com.model.PacienteDTO;
import com.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.logging.Logger;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
	private static final Logger logger = Logger.getLogger(OdontologoController.class.getName());
	
	@Autowired
	private OdontologoService odontologoService;
	

	@GetMapping("/{id}")
	public ResponseEntity<OdontologoDTO> buscar(@PathVariable Long id) throws BadRequestException, ResourceNotFoundException {
		logger.info("GET = Buscar el ODONTOLOGO con id" + id);
		OdontologoDTO odontologoDTO = odontologoService.buscarOdontologoPorID(id);
		return ResponseEntity.ok(odontologoDTO);
	}
	@GetMapping
	public ResponseEntity<Set<OdontologoDTO>> buscarTodos(){
		logger.info("GET = llamar todos los ODONTOLOGOS");
		return ResponseEntity.ok(odontologoService.listarOdontologos());
	}
	
	@PostMapping()
	public ResponseEntity<String> registrarOdontologo(@RequestBody OdontologoDTO odontologoDTO) {
		logger.info("POST = Crear nuevo ODONTOLOGO");
		odontologoService.crearOdontologo(odontologoDTO);
		return ResponseEntity.ok("se ha creado un nuevo odontologo");
	}
	
	
	@PutMapping()
	public ResponseEntity<String> actualizar(@RequestBody OdontologoDTO odontologoDTO) throws ResourceNotFoundException {
		logger.info("PUT = Modificar ODONTOLOGO " + odontologoDTO.getId());
		odontologoService.modificarOdontologo(odontologoDTO);
			return ResponseEntity.ok("se ha modificado el odontologo");
		}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Long id) throws ResourceNotFoundException {
		logger.info("DELETE = Borrar ODONTOLOGO " + id);
			odontologoService.eliminarOdontologo(id);
		return ResponseEntity.ok( id + "se ha eliminado el odontologo");
	}
	
}
