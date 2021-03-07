package com.example.demo;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.beans.BeanProperty;

@SpringBootApplication
public class DemoApplication {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository){
		return (args) -> {
			// save a few customers
			repository.save(new Customer("Robert", "Mulyukov"));
			repository.save(new Customer("Emil", "Sabitov"));
			repository.save(new Customer("Max", "Rubtsov"));
			repository.save(new Customer("Anton", "Trofimov"));
			repository.save(new Customer("Gendalf", "Mulyukov"));

			//fetch all customers
			log.info("Customers found with findAll(): ");
			log.info("--------------------------------");
			for(Customer customer : repository.findAll()){
				log.info(customer.toString());
			}
			log.info("");

			//fetch an individual customer by Id
			Customer customer = repository.findById(1L);
			log.info("Customer found with findById(1L): ");
			log.info("----------------------------------");
			log.info(customer.toString());
			log.info("");

			//fetch customers by last name
			log.info("Customer found with findByLastName('Mulyukov'): ");
			log.info("--------------------------------------");
			repository.findByLastName("Mulyukov").forEach(mulyukov -> {
				log.info(mulyukov.toString());
			});
			log.info("");
		};
	}

}
