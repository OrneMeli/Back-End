package com.service;

import com.config.exception.ResourceNotFoundException;
import com.model.OdontologoDTO;
import com.persistence.entity.Odontologo;
import com.persistence.repository.OdontologoRepositorio;
import com.service.implementation.IOdontologoService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Service
	public class OdontologoService implements IOdontologoService {
	@Autowired
	private OdontologoRepositorio odontologoRepository;
	@Autowired
	private ModelMapper mapper;
	
	private static final Logger logger = Logger.getLogger(OdontologoService.class.getName());
	public OdontologoService() {
	}
	
	@Override
	public void crearOdontologo(OdontologoDTO odontologoDTO) {
		Odontologo odontologo = mapper.map(odontologoDTO, Odontologo.class);
		odontologoRepository.save((odontologo));
		logger.info("odontologo guardado " + odontologo);
	}
	
	@Override
	public Set<OdontologoDTO> listarOdontologos() {
		List<Odontologo> listaOdontologos = odontologoRepository.findAll();
		Set<OdontologoDTO> listaOdontologosDTO = new HashSet<>();
		for(Odontologo odo:listaOdontologos) {
			listaOdontologosDTO.add(mapper.map(odo, OdontologoDTO.class));
		}
		return listaOdontologosDTO;
	}
	
	@Override
	public OdontologoDTO buscarOdontologoPorID(Long id) throws ResourceNotFoundException {
		if (odontologoRepository.existsById(id)) {
			Odontologo odontologoEncontrado = odontologoRepository.findById(id).get();
			OdontologoDTO odontologoDTO =  mapper.map(odontologoEncontrado, OdontologoDTO.class);
			return odontologoDTO;
		} else {
			throw new ResourceNotFoundException("odontologo incorrecto");
		}
	}
	
	@Override
	public void modificarOdontologo(OdontologoDTO odontologoDTO) throws ResourceNotFoundException {
		if (odontologoRepository.existsById(odontologoDTO.getId())) {
			Odontologo newOdontologo = mapper.map(odontologoDTO, Odontologo.class);
			odontologoRepository.save(newOdontologo);
			}
		else {
			throw new ResourceNotFoundException("odontologo incorrecto");
		}
		
	}
	
	@Override
	public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
		if (odontologoRepository.existsById(id)) {
			odontologoRepository.deleteById(id);;
		} else {
			throw new ResourceNotFoundException("odontologo incorrecto");
		}
	}
	}
	

