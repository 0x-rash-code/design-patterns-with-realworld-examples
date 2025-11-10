package com.example.design_patterns.creational.factory;

public class NotificationFactory {

    public  Notification createNotification(String type){

        return switch (type.toUpperCase()) {
            case "EMAIL" -> new EmailNotification();
            case "SMS" -> new SMSNotifications();
            case "WHATS APP" -> new WhatsAppNotification();
            default -> throw new IllegalArgumentException("Unknown notification type " + type);
        };
    }
}
