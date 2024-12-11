package com.project.customer_service;

import com.project.customer_service.entities.Customer;
import com.project.customer_service.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	// si je lance le test n'execute pas cette bean
	@Profile("!test")
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
		return args -> {
			customerRepository.save(Customer.builder()
							.firstName("Tantely")
							.lastName("Ravoson")
							.email("ravo@gmail.com")
					.build());
			customerRepository.save(Customer.builder()
					.firstName("James")
					.lastName("Lodwige")
					.email("james@gmail.com")
					.build());
			customerRepository.save(Customer.builder()
					.firstName("Julien")
					.lastName("Nambinintsoa")
					.email("julien@gmail.com")
					.build());
		};
	}

}
