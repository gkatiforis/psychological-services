package com.katiforis.spring.service.implemetations;

import com.katiforis.spring.Respones.NotificationResponse;
import com.katiforis.spring.dao.*;
import com.katiforis.spring.model.*;
import com.katiforis.spring.service.interfaces.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;


@Service("notificationService")
@Transactional
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationDao notificationDao;

    public void notifWelcome(User  receiver){
        addNotification(1,  receiver,  receiver);
    }
    public void notifAddConference(User  sender, User  receiver){
        addNotification(2,  ( sender),  ( receiver));
        addNotification(3,  ( receiver),  ( sender));
    }
    public void notifCancelConference(User  sender, User  receiver){
        addNotification(4,  ( sender),  ( receiver));
        addNotification(5,  ( receiver),  ( sender));
    }
    public void notifCancelOrder(User  sender, User  receiver){
        addNotification(6,  ( sender),  ( receiver));
        addNotification(7,  ( receiver),  ( sender));
    }

    public void notifBuyService(User  sender, User  receiver){
        addNotification(8,  ( sender),  ( receiver));
        addNotification(9,  ( receiver),  ( sender));
    }
    public void notifStartConference(User  sender, User  receiver){
        addNotification(10,  ( sender),  ( receiver));
        addNotification(11,  ( receiver),  ( sender));
    }

    public void notifComplteConference(User  sender, User  receiver){
        addNotification(12,  ( sender),  ( receiver));
        addNotification(13,  ( receiver),  ( sender));
    }

    public void notifBookNextConference(User  receiver){
        addNotification(14,  receiver,  receiver);
    }

    public void addNotification(Integer typeId, User  sender, User  receiver){
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        Notification notification = new Notification( sender,  receiver, new Notificationtype(typeId), now, false);
        notificationDao.save(notification);
    }

    public NotificationResponse getUserNotification(Integer userId, Integer seen){

        Integer newNotifNum = 0;
        List<Notification> notifications = notificationDao.findByUser(userId);
        if(seen == 1){
            for(Notification notification : notifications){
                notification.setSeenByUser(true);
            }
        }else{
            for(Notification notification : notifications){
                if(!notification.getSeenByUser()){
                    newNotifNum++;
                }
            }
        }

        return new NotificationResponse(newNotifNum, notifications);
    }
}
