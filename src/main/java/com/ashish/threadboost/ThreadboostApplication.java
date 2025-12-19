package com.ashish.threadboost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ThreadboostApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThreadboostApplication.class, args);
	}

}
