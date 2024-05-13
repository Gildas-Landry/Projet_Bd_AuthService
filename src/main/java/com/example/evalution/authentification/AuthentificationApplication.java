package com.example.evalution.authentification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude ={SecurityAutoConfiguration.class})
public class AuthentificationApplication {
	public static void main(String[] args) {
		SpringApplication.run(AuthentificationApplication.class, args);
	}

}
