package com.example.rest.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class RestConsumeDemoApplication {
	
	private static final Logger log  = LoggerFactory.getLogger(RestConsumeDemoApplication.class);
	//log.info()

	public static void main(String[] args) {
		
		SpringApplication.run(RestConsumeDemoApplication.class, args);
	}
	

}
