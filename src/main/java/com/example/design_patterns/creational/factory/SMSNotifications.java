package com.example.design_patterns.creational.factory;

public class SMSNotifications implements Notification {

    @Override
    public void notifyUser() {
        System.out.println("Sending an SMS notification");
    }
}
