package com.grab.retail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.grab.retail")
public class GrabStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrabStoreApplication.class, args);
	}

}
