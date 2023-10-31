package com.controller;

import com.config.exception.ResourceNotFoundException;
import com.model.TurnoDTO;
import com.service.OdontologoService;
import com.service.PacienteService;
import com.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.logging.Logger;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
	@Autowired
	private TurnoService turnoService;
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private OdontologoService odontologoService;
	private static final Logger logger = Logger.getLogger(TurnoController.class.getName());

	@GetMapping("/{id}")
	public ResponseEntity<TurnoDTO> buscar(@PathVariable Long id) throws ResourceNotFoundException {
		logger.info("GET = buscar TURNO con id" + id);
		TurnoDTO turnoEncontradoDTO = turnoService.buscarTurnoPorID(id);
		return ResponseEntity.ok(turnoEncontradoDTO);
	}
	
	@GetMapping()
	public ResponseEntity<Set<TurnoDTO>> buscarTodosLosTurnosOPorPaciente(@RequestParam(name="paciente_id", required= false) Long id, @RequestParam(name="date", required = false) String date) throws ResourceNotFoundException, ParseException {
		if(id == null) {
				if (date == null) {
				logger.info("GET = llamar a todos los TURNOS");
				return ResponseEntity.ok(turnoService.listarTurnos());
				} else
				  {
				logger.info("GET = lamara todos los TURNOS de una fecha");
				Set<TurnoDTO> turnosPorFecha = turnoService.buscarTurnosPorFecha(date);
				return ResponseEntity.ok(turnoService.listarTurnos()); }
		} else {
				logger.info("GET = Buscar todos los TURNOS segun paciente y fecha " + id + date );
				Set<TurnoDTO> turnosEncontradosDTO = turnoService.buscarTurnosPorPacienteYFecha(id, date);
				return ResponseEntity.ok(turnosEncontradosDTO);
		}
	}
	
	@PostMapping
		public ResponseEntity<String> registrarTurno(@RequestBody TurnoDTO turnoDTO) throws ResourceNotFoundException {
		logger.info("POST = crear TURNO");
		turnoService.crearTurno(turnoDTO);
			return ResponseEntity.ok("Turno creado");
	}
	
	@PutMapping
	public ResponseEntity<String> actualizar(@RequestBody TurnoDTO turnoDTO) throws ResourceNotFoundException {
		logger.info("PUT = modificar TURNO" + turnoDTO.getId());
		turnoService.modificarTurno(turnoDTO);
		return ResponseEntity.ok("Turno modificado");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Integer id) {
		logger.info("DELETE = Eliminar TURNO " + id);
	return null;
	}
	
}
