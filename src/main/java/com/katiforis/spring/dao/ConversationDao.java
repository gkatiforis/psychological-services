package com.katiforis.spring.dao;

import com.katiforis.spring.model.Conversation;
import com.katiforis.spring.model.Notification;
import com.katiforis.spring.model.User;

import java.util.List;

public interface ConversationDao {

    Conversation findById(int id);
    void update(Conversation conversation);
    void save(Conversation conversation);
    List<Conversation> findByUser(User user);
    boolean conversationExist(User user, User user1);
}