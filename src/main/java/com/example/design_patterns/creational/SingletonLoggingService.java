package com.example.design_patterns.creational;

public class SingletonLoggingService {

    // Step 1: Create a private static instance
    private static SingletonLoggingService loggingService;

    // Step 2: Private constructor prevents external instantiation
    private SingletonLoggingService(){
        System.out.println("Logging Service Initialized");
    }

    // Step 3: Public static method to get the single instance
    public static SingletonLoggingService getInstance(){
        if (loggingService == null){
            loggingService = new SingletonLoggingService(); // Create the instance if it doesn't exist
        }
        return loggingService;
    }

    public void log(String message){
        System.out.println("[Log] : " + message);
    }
}
