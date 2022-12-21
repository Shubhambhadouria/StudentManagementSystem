package com.platformcommons.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication

public class StudentManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}

}
