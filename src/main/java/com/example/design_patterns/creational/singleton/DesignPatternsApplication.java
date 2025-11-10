package com.example.design_patterns.creational.singleton;

import java.lang.reflect.Constructor;

public class DesignPatternsApplication {

	public static void main(String[] args) {
        SingletonLoggingService loggingService = SingletonLoggingService.getInstance();

        Constructor<SingletonLoggingService> constructor = null;
        try {
            constructor = SingletonLoggingService.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            SingletonLoggingService logger2 = constructor.newInstance();
            logger2.log("Logging from reflection-created instance");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        loggingService.log("Application Started");
        SingletonLoggingService loggingService1 = SingletonLoggingService.getInstance();
        loggingService1.log("Logging from second instance");
        System.out.println("logger1 == logger2: " + (loggingService == loggingService1));


	}

}
