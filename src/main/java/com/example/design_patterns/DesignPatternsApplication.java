package com.example.design_patterns;

import com.example.design_patterns.creational.SingletonLoggingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DesignPatternsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesignPatternsApplication.class, args);
        SingletonLoggingService loggingService = SingletonLoggingService.getInstance();
        loggingService.log("Application Started");

        SingletonLoggingService loggingService1 = SingletonLoggingService.getInstance();
        loggingService1.log("Logging from second instance");

        System.out.println("logger1 == logger2: " + (loggingService == loggingService1));
	}

}
