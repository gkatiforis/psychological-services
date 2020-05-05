package com.katiforis.spring.dao;

import com.katiforis.spring.model.Conversation;
import com.katiforis.spring.model.Notification;
import com.katiforis.spring.model.User;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("conversationDao")
public class ConversationDaoImpl extends AbstractDao<Integer, Conversation> implements ConversationDao {

    static final Logger logger = LoggerFactory.getLogger(ConversationDaoImpl.class);

    public Conversation findById(int id) {
        Conversation conversation = getByKey(id);
        return conversation;
    }
    public void update(Conversation conversation) {
        update(conversation);
    }
    public void save(Conversation conversation){
        persist(conversation);
    }


    public boolean conversationExist(User user, User user1){

        String hql ="select n from Conversation n " +
                " join fetch n.users su" +
                " where (su.id = :userId1 and " +
                " n.id in  "+
                " (select c.id from Conversation c " +
                " left join c.users cu" +
                " where (" +
                " cu.id  = :userId2)))";

        Query query = getSession().createQuery(hql);

        List<Conversation> conversations = (List<Conversation>) query
                .setParameter("userId1", user.getId())
                .setParameter("userId2", user1.getId())
                .list();

        if(conversations.size() > 0) return true;
        return false;
    }

    public List<Conversation> findByUser(User user){

        String hql ="select n from Conversation n " +
                " join fetch n.users su" +
                " where (su.id != :userId and " +
                " n.id in  "+
                " (select c.id from Conversation c " +
                " left join c.users cu" +
                " where (" +
                " cu.id  = :userId)))";


        Query query = getSession().createQuery(hql);

        List<Conversation> conversations = (List<Conversation>) query
                .setParameter("userId", user.getId())
                .setMaxResults(50)
                .list();

        return conversations;
    }
}