package com.anand;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PertTelecomApplication {

	public static void main(String[] args) {
		SpringApplication.run(PertTelecomApplication.class, args);
		System.out.println("Welcome To Pert Telecom Pvt.LTD");
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	


}
