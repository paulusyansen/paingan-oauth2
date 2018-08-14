package org.paingan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class PainganOauth2Application {

	public static void main(String[] args) {
		SpringApplication.run(PainganOauth2Application.class, args);
	}
}
