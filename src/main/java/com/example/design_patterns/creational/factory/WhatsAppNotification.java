package com.example.design_patterns.creational.factory;

public class WhatsAppNotification implements Notification{
    @Override
    public void notifyUser() {
        System.out.println("Sending a WhatsApp notification");
    }
}
