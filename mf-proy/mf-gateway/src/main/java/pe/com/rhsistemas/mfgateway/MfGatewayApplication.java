package pe.com.rhsistemas.mfgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Controller;

@EnableZuulProxy
@Controller
@SpringBootApplication
public class MfGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MfGatewayApplication.class, args);
	}

}
