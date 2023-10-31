package com.example.tpfinal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.persistence.entity.Domicilio;
import com.persistence.entity.Odontologo;
import com.persistence.entity.Paciente;
import com.persistence.entity.Turno;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegrationTests {
	@Autowired
	private MockMvc mockMvc;
	
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("YYYY-mm-dd");
	private static final Logger logger = Logger.getLogger(IntegrationTests.class.getName());
	@Before
	public void crearRegistrosEnBD() throws Exception {
		logger.info("Creando registros en la base de datos...");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		
		Odontologo od = new Odontologo(1L, 87088,"gabriel", "morello");
		Paciente pac = new Paciente(1L, "federico", "ras", "1234567", dateFormatter.parse("2027-10-11") , new Domicilio("calle1", "1234", "CABA", "CABA"));
		Turno t = new Turno(pac, od, dateFormatter.parse("2023-12-29"));
		
		MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/pacientes")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(pac)))
				.andDo(result -> mockMvc.perform(MockMvcRequestBuilders.post("/odontologos")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(od))))
				.andDo(result -> mockMvc.perform(MockMvcRequestBuilders.post("/turnos")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(t))))
						.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		logger.info("se han creado los registros");
	}
	
	
	@Test
	public void registrarOdontologo() throws Exception {
		//when
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		
		Odontologo od = new Odontologo(87088,"gabriel", "morello");
		MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/odontologos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(od)))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		//then
		Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
	}
	
	@Test
	public void registrarTurno() throws Exception {
		//when
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		
		Odontologo od = new Odontologo(1L, 87088,"fede", "ras");
		Paciente pac = new Paciente(1L, "angel", "aaa", "1233412", dateFormatter.parse("2023-01-10") , new Domicilio("calle1", "1234", "CABA", "CABA"));
		System.out.println(pac);
		
		
		Turno t = new Turno(pac, od, dateFormatter.parse("2023-04-10"));
		MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/pacientes")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(pac)))
						.andDo(result -> mockMvc.perform(MockMvcRequestBuilders.post("/odontologos")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(od))))
						.andDo(result -> mockMvc.perform(MockMvcRequestBuilders.post("/turnos")
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(t)))
						.andDo(MockMvcResultHandlers.print())
						).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		//then
		Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
	}
	@Test
	public void buscarTurnoPorPaciente() throws Exception {
		//when
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		
		Odontologo od = new Odontologo(1L, 87088,"laura", "rodriguez");
		Paciente pac = new Paciente(1L, "jorge", "regalini", "7823123", dateFormatter.parse("2023-01-10") , new Domicilio("calle1", "1234", "CABA", "CABA"));
		Turno t = new Turno(pac, od, dateFormatter.parse("2023-11-29"));
		
		MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.get("/turnos?paciente=1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(t)))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		//then
		Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
	}
	
	@Test
	public void buscarTurnoPorFechaYPaciente() throws Exception {
		//when
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		
		Odontologo od = new Odontologo(1L, 87088,"gabriel", "morello");
		Paciente pac = new Paciente(1L, "agustin", "ras", "12334567", dateFormatter.parse("2023-01-10") , new Domicilio("calle2", "1234", "CABA", "CABA"));
		Turno t = new Turno(pac, od, dateFormatter.parse("2023-11-29"));
		
		MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.get("/turnos?date=2023-10-10&paciente_id=1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(t)))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		//then
		Assert.assertFalse(response.getResponse().getContentAsString().isEmpty());
	}
	
	
}
