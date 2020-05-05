package com.katiforis.spring.dao;

import com.katiforis.spring.model.Notification;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("notificationDao")
public class NotificationDaoImpl extends AbstractDao<Integer, Notification> implements NotificationDao {

    static final Logger logger = LoggerFactory.getLogger(NotificationDaoImpl.class);

    public Notification findById(int id) {
        Notification notification = getByKey(id);
        return notification;
    }

    public void update(Notification notification) {
        update(notification);
    }

    public void save(Notification notification){
        persist(notification);
    }

    public List<Notification> findByUser(Integer userId){

        String hql = "select n from Notification n " +
                " left join fetch n.senderUser su" +
                " left join fetch n.receiverUser ru" +
                " left join fetch n.notificationType t " +
                "where (" +
                "ru.id  = :userId) order by n.notificationDate desc ";

        Query query = getSession().createQuery(hql);

        List<Notification> notifications = (List<Notification>) query
                .setParameter("userId", userId)
                .setMaxResults(50)
                .list();
        return notifications;
    }
}