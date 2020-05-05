package com.katiforis.spring.dao;

import com.katiforis.spring.model.ChatMessage;
import com.katiforis.spring.model.Conversation;
import com.katiforis.spring.model.User;

import java.util.List;

public interface ChatMessageDao {
    ChatMessage findById(int id);
    void update(ChatMessage chatMessage);
    void save(ChatMessage chatMessage);
    List<ChatMessage> findMessagesByConversation(Integer conversationId);
}