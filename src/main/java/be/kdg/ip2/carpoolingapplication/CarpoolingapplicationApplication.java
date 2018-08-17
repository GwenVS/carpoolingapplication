package be.kdg.ip2.carpoolingapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
@EntityScan
public class CarpoolingapplicationApplication {
	public static void main(String[] args) {
		SpringApplication.run(CarpoolingapplicationApplication.class, args);
	}
}