package pe.com.rhsistemas.mfjpaconfiguracion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MfJpaConfiguracionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MfJpaConfiguracionApplication.class, args);
	}

}
