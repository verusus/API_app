package com.entrepriseName;

import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(ApiAppApplication.class, args);

		Faker faker = new Faker();

		String name = faker.name().fullName();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String streetAddress = faker.address().streetAddress();

		System.out.println("fullName: "  + name+ " |firstName: " + firstName + " |lastName: " + lastName +
				" |streetAddress: " + streetAddress);
	}
}
