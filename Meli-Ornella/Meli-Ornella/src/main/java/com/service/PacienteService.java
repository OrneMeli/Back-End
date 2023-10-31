package com.service;

import com.config.exception.ResourceNotFoundException;
import com.model.PacienteDTO;
import com.persistence.entity.Domicilio;
import com.persistence.entity.Paciente;
import com.persistence.repository.DomicilioRepository;
import com.persistence.repository.PacienteRepositorio;
import com.service.implementation.IPacienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class PacienteService implements IPacienteService {
	@Autowired
	private PacienteRepositorio pacienteRepository;
	@Autowired
	DomicilioRepository domicilioRepository;
	@Autowired
	private ModelMapper mapper;
	
	private static final Logger logger = Logger.getLogger(PacienteService.class.getName());
	public PacienteService(PacienteRepositorio pacienteRepository) {
		this.pacienteRepository = pacienteRepository;
	}
	
	@Override
	public void crearPaciente(PacienteDTO pacienteDTO) {
		Paciente paciente = mapper.map(pacienteDTO, Paciente.class);
		Domicilio domicilio = mapper.map(pacienteDTO.getDomicilio(), Domicilio.class);
		pacienteRepository.save(paciente);
		logger.info("paciente guardado " + paciente);
		domicilioRepository.save(domicilio);
		logger.info("domicilio guardado " + domicilio);
	}
	
	@Override
	public Set<PacienteDTO> listarPacientes() {
		List<Paciente> listaPacientes = pacienteRepository.findAll();
		Set<PacienteDTO> listaPacientesDTO = new HashSet<>();
		for(Paciente pac:listaPacientes) {
			listaPacientesDTO.add(mapper.map(pac, PacienteDTO.class));
		}
		return listaPacientesDTO;
	}
	
	@Override
	public PacienteDTO buscarPacientePorID(Long id) throws ResourceNotFoundException {
		if (pacienteRepository.existsById(id)) {
			Paciente pacienteEncontrado = pacienteRepository.findById(id).get();
			PacienteDTO pacienteDTO =  mapper.map(pacienteEncontrado, PacienteDTO.class);
			return pacienteDTO;
		} else {
			throw new ResourceNotFoundException("paciente inexistente");
		}
	}
	
	@Override
	public void modificarPaciente(PacienteDTO pacienteDTO) throws ResourceNotFoundException {
		if (pacienteRepository.existsById(pacienteDTO.getId())) {
		Paciente newPaciente = mapper.map(pacienteDTO, Paciente.class);
		pacienteRepository.save(newPaciente);}
		else {
			throw new ResourceNotFoundException("paciente inexistente");
		}
	}
	
	@Override
	public void eliminarPaciente(Long id) throws ResourceNotFoundException {
			if (pacienteRepository.existsById(id)) {
			pacienteRepository.deleteById(id);
			} else {
				throw new ResourceNotFoundException("paciente inexistente");
			}
	}
	
}
