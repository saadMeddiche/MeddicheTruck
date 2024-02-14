package com.MeddicheTruck.mtmain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@ComponentScan(basePackages = {"com.MeddicheTruck.mtcore" ,"com.MeddicheTruck.mtsecurity"})
@ComponentScan(basePackages = {"com.MeddicheTruck.mtcore"})
public class MtMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MtMainApplication.class, args);
	}

}
