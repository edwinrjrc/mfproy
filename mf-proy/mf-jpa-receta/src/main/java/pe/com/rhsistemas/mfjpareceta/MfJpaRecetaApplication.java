package pe.com.rhsistemas.mfjpareceta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MfJpaRecetaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MfJpaRecetaApplication.class, args);
	}

}
