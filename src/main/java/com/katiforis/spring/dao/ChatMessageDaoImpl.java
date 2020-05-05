package com.katiforis.spring.dao;

import com.katiforis.spring.model.ChatMessage;
import com.katiforis.spring.model.Conversation;
import com.katiforis.spring.model.User;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("chatmessagedao")
public class ChatMessageDaoImpl extends AbstractDao<Integer, ChatMessage> implements ChatMessageDao {
    static final Logger logger = LoggerFactory.getLogger(ChatMessageDaoImpl.class);

    public ChatMessage findById(int id) {
        ChatMessage chatMessage = getByKey(id);
        return chatMessage;
    }
    public void update(ChatMessage chatMessage) {
        update(chatMessage);
    }
    public void save(ChatMessage chatMessage){
        persist(chatMessage);
    }

    public List<ChatMessage> findMessagesByConversation(Integer conversationId){

        String hql = "select c from ChatMessage c " +
                "where (" +
                "c.conversation.id = :conversationId) order by c.sendDate ";


        Query query = getSession().createQuery(hql);

        List<ChatMessage> chatMessages = (List<ChatMessage>) query
                .setParameter("conversationId", conversationId)
                .setMaxResults(200)
                .list();
        return chatMessages;
    }
}