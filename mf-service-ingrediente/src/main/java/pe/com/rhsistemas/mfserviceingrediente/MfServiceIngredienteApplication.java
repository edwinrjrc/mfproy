package pe.com.rhsistemas.mfserviceingrediente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MfServiceIngredienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MfServiceIngredienteApplication.class, args);
	}

}
