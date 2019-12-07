package org.cbr.test.apachcamelrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class ApacheCamelRestApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ApacheCamelRestApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "4200"));
		app.run(args);
	}

}
