package org.paingan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PainganOauth2Application {

	public static void main(String[] args) {
		SpringApplication.run(PainganOauth2Application.class, args);
	}
}
