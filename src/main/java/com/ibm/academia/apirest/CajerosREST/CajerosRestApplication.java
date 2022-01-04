package com.ibm.academia.apirest.CajerosREST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CajerosRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CajerosRestApplication.class, args);
	}

}
