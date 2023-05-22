package pe.com.rhsistemas.mfservicemail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MfServiceMailApplication {

	public static void main(String[] args) {
		SpringApplication.run(MfServiceMailApplication.class, args);
	}

}
