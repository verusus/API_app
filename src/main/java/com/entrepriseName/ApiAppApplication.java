package com.entrepriseName;

import com.entrepriseName.service.UserService;
import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;

@SpringBootApplication
public class ApiAppApplication {

	public static void main(String[] args) throws ParseException {

		SpringApplication.run(ApiAppApplication.class, args);

	}
}
