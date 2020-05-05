package com.katiforis.spring.Respones;

import com.katiforis.spring.model.Notification;

import java.util.List;

public class NotificationResponse {
    Integer numOfNotifications;
    List<Notification> notifications;

    public NotificationResponse(Integer numOfNotifications, List<Notification> notifications) {
        this.numOfNotifications = numOfNotifications;
        this.notifications = notifications;
    }

    public Integer getNumOfNotifications() {
        return numOfNotifications;
    }

    public void setNumOfNotifications(Integer numOfNotifications) {
        this.numOfNotifications = numOfNotifications;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
