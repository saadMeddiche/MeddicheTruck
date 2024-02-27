package com.MeddicheTruck.mtmain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;


@SpringBootApplication
@ComponentScan(basePackages = {"com.MeddicheTruck.mtcore" ,"com.MeddicheTruck.mtsecurity"})
//@ComponentScan(basePackages = {"com.MeddicheTruck.mtcore"})
public class MtMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MtMainApplication.class, args);
	}


}
