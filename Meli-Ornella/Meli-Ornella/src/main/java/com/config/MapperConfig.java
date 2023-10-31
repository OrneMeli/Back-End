package com.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
	
	public MapperConfig() {};
	
	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}
}
