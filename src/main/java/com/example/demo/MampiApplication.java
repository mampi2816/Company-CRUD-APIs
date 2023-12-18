package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MampiApplication {
	
	public static final Logger LOGGER = LogManager.getLogger(MampiApplication.class);

	public static void main(String[] args) {
		
		SpringApplication.run(MampiApplication.class, args);
		LOGGER.info("Server started at port 8083");
	}

}
