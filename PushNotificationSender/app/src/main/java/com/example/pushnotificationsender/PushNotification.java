package com.example.pushnotificationsender;

public class PushNotification {
    private Notification notification;
    private String to;
    public PushNotification(Notification notification, String to) {
        this.notification = notification;
        this.to = to;
    }
    public PushNotification(Notification data) {
        this.notification = data;
    }
    public Notification getNotification() {
        return notification;
    }
    public void setNotification(Notification data) {
        this.notification = data;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String recipient) {
        this.to = recipient;
    }
}
