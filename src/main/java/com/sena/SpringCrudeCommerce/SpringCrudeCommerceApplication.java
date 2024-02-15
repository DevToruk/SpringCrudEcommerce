package com.sena.SpringCrudeCommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringCrudeCommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCrudeCommerceApplication.class, args);
	}

}
