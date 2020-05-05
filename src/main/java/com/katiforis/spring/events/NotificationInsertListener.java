package com.katiforis.spring.events;

import com.katiforis.spring.Respones.SubmissionEvent;
import com.katiforis.spring.model.Notification;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class NotificationInsertListener implements PostInsertEventListener {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void onPostInsert(PostInsertEvent event) {
        if (event.getEntity() instanceof Notification) {
            Notification notification = (Notification) event.getEntity();

            SubmissionEvent submissionEvent = new SubmissionEvent( this, Long.valueOf(notification.getReceiverUser().getId()), "1");
            eventPublisher.publishEvent(submissionEvent);

        }
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister persister) {
        return false;
    }
}
