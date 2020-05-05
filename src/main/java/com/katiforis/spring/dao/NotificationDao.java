package com.katiforis.spring.dao;

import com.katiforis.spring.model.Notification;

import java.util.List;

public interface NotificationDao {
    Notification findById(int id);
    void update(Notification notification);
    void save(Notification notification);
    List<Notification> findByUser(Integer userId);

}