package com.katiforis.spring.service.interfaces;

import com.katiforis.spring.Respones.NotificationResponse;
import com.katiforis.spring.model.Conference;
import com.katiforis.spring.model.User;

import java.util.Date;
import java.util.List;

public interface NotificationService {
    void addNotification(Integer typeId, User sender, User receiver);
    void notifWelcome(User receiver);
    void notifAddConference(User sender, User receiver);
    void notifCancelConference(User sender, User receiver);
    void notifCancelOrder(User sender, User receiver);
    void notifBuyService(User sender, User receiver);
    void notifStartConference(User sender, User receiver);
    void notifComplteConference(User sender, User receiver);
    void notifBookNextConference(User receiver);
    NotificationResponse getUserNotification(Integer userId, Integer seen);
}
