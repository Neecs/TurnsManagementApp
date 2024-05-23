package edu.uptc.swii.TurnsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class TurnsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TurnsServiceApplication.class, args);
	}

}
