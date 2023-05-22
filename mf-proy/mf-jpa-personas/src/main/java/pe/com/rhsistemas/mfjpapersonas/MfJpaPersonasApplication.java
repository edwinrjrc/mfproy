package pe.com.rhsistemas.mfjpapersonas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MfJpaPersonasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MfJpaPersonasApplication.class, args);
	}

}
