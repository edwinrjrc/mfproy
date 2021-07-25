package pe.com.rhsistemas.mfjpaplatos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MfJpaPlatosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MfJpaPlatosApplication.class, args);
	}

}
