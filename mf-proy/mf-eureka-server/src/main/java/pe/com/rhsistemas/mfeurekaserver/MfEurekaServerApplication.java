package pe.com.rhsistemas.mfeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MfEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MfEurekaServerApplication.class, args);
	}

}
