package com.service;

import com.config.exception.ResourceNotFoundException;
import com.model.TurnoDTO;
import com.persistence.entity.Turno;
import com.persistence.repository.OdontologoRepositorio;
import com.persistence.repository.PacienteRepositorio;
import com.persistence.repository.TurnoRepositorio;
import com.service.implementation.ITurnoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class TurnoService implements ITurnoService {
	@Autowired
	private TurnoRepositorio turnoRepository;
	@Autowired
	private PacienteRepositorio pacienteRepository;
	@Autowired
	private OdontologoRepositorio odontologoRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	private static final Logger logger = Logger.getLogger(TurnoService.class.getName());
	SimpleDateFormat dateFormatter = new SimpleDateFormat("YYYY-mm-dd");
	public TurnoService() {
		this.turnoRepository = turnoRepository;
	}
	
	@Override
	public void crearTurno(TurnoDTO turnoDTO) throws ResourceNotFoundException {
		Turno turno = mapper.map(turnoDTO, Turno.class);
		if(pacienteRepository.existsById(turno.getPaciente().getId()) || odontologoRepository.existsById(turno.getOdontologo().getId())) {
			turnoRepository.save(turno);
			turno.getPaciente().addTurnoToListaDeTurnos(turno);
			turno.getOdontologo().addTurnoToListaDeTurnos(turno);
		} else {
			throw new ResourceNotFoundException("Paciente u odontologo inexistente");
		}
	}
	
	@Override
	public Set<TurnoDTO> listarTurnos() {
		List<Turno> listaTurnos = turnoRepository.findAll();
		Set<TurnoDTO> listaTurnosDTO = new HashSet<>();
		for(Turno tur:listaTurnos) {
			listaTurnosDTO.add(mapper.map(tur, TurnoDTO.class));
		}
		return listaTurnosDTO;
	}
	
	@Override
	public TurnoDTO buscarTurnoPorID(Long id) throws ResourceNotFoundException {
		if(turnoRepository.existsById(id)) {
			Turno turnoEncontrado = turnoRepository.findById(id).get();
			TurnoDTO turnoDTO = mapper.map(turnoEncontrado, TurnoDTO.class);
			return turnoDTO;
		} else {
			throw new ResourceNotFoundException("id inexistente");
		}
	}
	
	@Override
	public void modificarTurno(TurnoDTO turnoDTO) throws ResourceNotFoundException {
		if (turnoRepository.existsById(turnoDTO.getId())) {
			Turno turno = mapper.map(turnoDTO, Turno.class);
			turnoRepository.save(turno);
			}
		else {
			throw new ResourceNotFoundException("turno iexistente");
		}
	}
	
	@Override
	public void eliminarTurno(Long id) throws ResourceNotFoundException {
		if (turnoRepository.existsById(id)) {
			turnoRepository.deleteById(id);
		} else {
			throw new ResourceNotFoundException("turno iexistente");
		}
	}

	@Override
	public Set<TurnoDTO> buscarTurnosPorPaciente(Long id) throws ResourceNotFoundException {
		if(pacienteRepository.existsById(id)) {
			Set<Turno> turnosDePaciente = turnoRepository.buscarTurnosPorPaciente(id);
			Set<TurnoDTO> turnosDTODePaciente = new HashSet<>();
			for(Turno t:turnosDePaciente) {
				turnosDTODePaciente.add( mapper.map(t, TurnoDTO.class));
			}
			return turnosDTODePaciente;
		} else {
			throw new ResourceNotFoundException("turno iexistente");
		}
	}
	
	@Override
	public Set<TurnoDTO> buscarTurnosPorFecha(String date) throws ParseException {
		Date newDate = dateFormatter.parse(date);
		Set<Turno> turnosDePaciente = turnoRepository.buscarTurnosPorFecha(newDate);
		Set<TurnoDTO> turnosDTODePaciente = new HashSet<>();
			for(Turno t:turnosDePaciente) {
				turnosDTODePaciente.add( mapper.map(t, TurnoDTO.class));
			}
			return turnosDTODePaciente;
	}
	
	@Override
	public Set<TurnoDTO> buscarTurnosPorPacienteYFecha(Long id, String date) throws ParseException {
		Date newDate = dateFormatter.parse(date);
		Set<Turno> turnosPorFecha = turnoRepository.buscarTurnosPorFecha(newDate);
		Set<TurnoDTO> turnosDTOPorFechaYPaciente = new HashSet<>();
		for(Turno t:turnosPorFecha) {
			if (t.getPaciente().getId().equals(id)) {
			turnosDTOPorFechaYPaciente.add(mapper.map(t, TurnoDTO.class));
		}
		}
		return turnosDTOPorFechaYPaciente;
	}
}
