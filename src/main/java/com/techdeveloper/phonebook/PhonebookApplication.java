package com.techdeveloper.phonebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class PhonebookApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhonebookApplication.class, args);
	}

}
