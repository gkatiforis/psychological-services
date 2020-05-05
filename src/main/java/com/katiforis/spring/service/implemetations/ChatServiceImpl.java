package com.katiforis.spring.service.implemetations;

import com.katiforis.spring.Respones.Chat;
import com.katiforis.spring.dao.ChatMessageDao;
import com.katiforis.spring.dao.ConversationDao;
import com.katiforis.spring.model.*;
import com.katiforis.spring.service.interfaces.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;


@Service("chatService")
@Transactional
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatMessageDao chatMessageDao;
    @Autowired
    private ConversationDao conversationDao;

    @Autowired
    private  SimpMessageSendingOperations messagingTemplate;

    public void addConversation(Set<User> users){
        if(!conversationExist(users)){
            Conversation conversation = new Conversation(users);
            conversationDao.save(conversation);
        }
    }

    public boolean conversationExist(Set<User> users){
        List<User> lusers = new ArrayList<>(users);
        Conversation conversation = new Conversation(users);
        return conversationDao.conversationExist(lusers.get(0), lusers.get(1));
    }

    public List<Chat> getConversationMessages(User sender, Integer conversationId){

        List<ChatMessage> chatMessages =  chatMessageDao.findMessagesByConversation(conversationId);
        List<Chat> chats = new ArrayList<>();
        for (ChatMessage chatMessage : chatMessages){
            Chat chat = new Chat();
            chat.setMessage(chatMessage.getMessage());
            chat.setFrom(chatMessage.getUser().getSsoId());
            chat.setConversationId(chatMessage.getConversation().getId());
            chat.setSendDate(chatMessage.getSendDate());
            chats.add(chat);
        }
        return  chats;
    }
    public Integer sendMessage(User sender, Chat chat){
        Calendar now = Calendar.getInstance();
        chat.setSendDate(now.getTime());
        Conversation conversation =  conversationDao.findById(chat.getConversationId());
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setUser(sender);
        chatMessage.setConversation(conversation);
        chatMessage.setMessage(chat.getMessage());
        chatMessage.setSeenByUser(false);
        chatMessage.setSendDate(chat.getSendDate());
        chatMessage.setDeleted(false);

        chatMessageDao.save(chatMessage);

        //chat.setFrom(sender.getSsoId());

        for(User user : conversation.getUsers()){
            messagingTemplate.convertAndSendToUser(user.getSsoId(), "/queue/chats", chat);
        }
        return 1;
    }

    public List<Conversation> getUserConversation(User user){
        List<Conversation> conversations = conversationDao.findByUser(user);
        return  conversations;
    }
}
