package com.aro.FechamentoAro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FechamentoAroApplication {

	public static void main(String[] args) {
		SpringApplication.run(FechamentoAroApplication.class, args);
	}

}
