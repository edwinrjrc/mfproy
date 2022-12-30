package pe.com.rhsistemas.mfserconfiguracion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MfServiceConfiguracionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MfServiceConfiguracionApplication.class, args);
	}

}
