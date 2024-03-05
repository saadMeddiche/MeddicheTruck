package com.MeddicheTruck.mtsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.MeddicheTruck.mtcore" ,"com.MeddicheTruck.mtsecurity"})
public class MtSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(MtSecurityApplication.class, args);
	}

}
