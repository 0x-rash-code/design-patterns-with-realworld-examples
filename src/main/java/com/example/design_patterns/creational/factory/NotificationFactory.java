package com.example.design_patterns.creational.factory;

public class NotificationFactory {

    public  Notification createNotification(String type){
        if (type.equalsIgnoreCase("SMS")){
            return new SMSNotifications();
        } else if (type.equalsIgnoreCase("EMAIL")){
            return new EmailNotification();
        } else {
            throw new IllegalArgumentException("Unknow notification type " + type);
        }
    }

}
