package com.HumanAIdeJobAppMs.JobApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class HumanAideJobAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HumanAideJobAppApplication.class, args);
	}

}
