package com.example.design_patterns.creational.factory;

public class FactoryMethodDemo {

    public static void main(String[] args) {
        NotificationFactory factory = new NotificationFactory();

        Notification notification = factory.createNotification("SMS");
        notification.notifyUser();

        notification = factory.createNotification("EMAIL");
        notification.notifyUser();
    }
}
