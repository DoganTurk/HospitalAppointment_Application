package com.sabanciuniv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.sabanciuniv.*"})
@EnableMongoRepositories(basePackages = "com.sabanciuniv.repo")
public class HospitalApplication1Application {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication1Application.class, args);
	}

}
