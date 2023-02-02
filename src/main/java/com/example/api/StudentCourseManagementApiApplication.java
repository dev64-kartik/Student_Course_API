package com.example.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class StudentCourseManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentCourseManagementApiApplication.class, args);
	}

}
