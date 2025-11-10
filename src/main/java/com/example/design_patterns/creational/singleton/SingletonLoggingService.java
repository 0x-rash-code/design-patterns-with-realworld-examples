package com.example.design_patterns.creational.singleton;

public class SingletonLoggingService {

    // Step 1: Create a private static instance
    private static SingletonLoggingService loggingService;

    // Step 2: Private constructor prevents external instantiation
    private SingletonLoggingService(){
        if (loggingService != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
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
