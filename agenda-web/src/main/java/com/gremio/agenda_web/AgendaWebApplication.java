package com.gremio.agenda_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.gremio")
@EnableJpaRepositories(basePackages = "com.gremio.repository")
@EntityScan(basePackages = "com.gremio.model")
public class AgendaWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendaWebApplication.class, args);
	}

}
