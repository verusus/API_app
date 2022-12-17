package com.entrepriseName;

import com.entrepriseName.service.UserService;
import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class ApiAppApplication {

	public static void main(String[] args) throws ParseException {

		SpringApplication.run(ApiAppApplication.class, args);

//		Pattern p;
//		Matcher m;
//		//tous les chiffre de 0 à 9 sauf 345
////		p = Pattern.compile("[0-9&&[^345]]"); intersection
//		p = Pattern.compile("a{3}");
//		m = p.matcher("aaa");
//		boolean b = m.matches();
//		System.out.println(b);

//		String regex = "(^\\d{3})(\\d{4})(\\d{3})(\\d{3})$";
//		Pattern p = Pattern.compile(regex);
//		String tel = "2541724156348";
//
//		Matcher m = p.matcher(tel);
//
//		if (m.find()) {
//			System.out.println("Téléphone : ("
//					+ m.group(1) + ") " + m.group(2) + "-"
//					+ m.group(3) + "-" + m.group(4));
//		}
	}
}
