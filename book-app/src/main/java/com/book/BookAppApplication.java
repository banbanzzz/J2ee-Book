package com.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
public class BookAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookAppApplication.class, args);
	}

}
