package com.ditsov.basicschoolgradingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BasicSchoolGradingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicSchoolGradingSystemApplication.class, args);
	}

}
