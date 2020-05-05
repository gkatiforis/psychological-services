package com.katiforis.spring.service.interfaces;

import com.katiforis.spring.Respones.Chat;
import com.katiforis.spring.Respones.NotificationResponse;
import com.katiforis.spring.model.ChatMessage;
import com.katiforis.spring.model.Conversation;
import com.katiforis.spring.model.User;

import java.security.Principal;
import java.util.List;
import java.util.Set;

//TODO: online/offline user state on chat
//TODO: show unread by user messages
public interface ChatService {
    Integer sendMessage(User user, Chat chat);
    List<Conversation> getUserConversation(User user);
    List<Chat> getConversationMessages(User sender, Integer conversationId);
    void addConversation(Set<User> users);
    boolean conversationExist(Set<User> users);
}
