package com.katiforis.spring.model;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="notification")
public class Notification {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "senderUserId", nullable = false)
    private User senderUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiverUserId", nullable = false)
    private User receiverUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typeID", nullable = false)
    private Notificationtype notificationType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "notificationDate", nullable = false)
    private Date notificationDate;

    @Column(name = "seenByUser", nullable = false)
    private Boolean seenByUser;

    public Notification(){}
    public Notification(User senderUser, User receiverUser, Notificationtype notificationType, Date notificationDate, Boolean seenByUser) {
        this.notificationType = notificationType;
        this.notificationDate = notificationDate;
        this.seenByUser = seenByUser;
        this.senderUser=senderUser;
        this.receiverUser =receiverUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public User getSenderUser() {
        return senderUser;
    }

    public void setSenderUser(User senderUser) {
        this.senderUser = senderUser;
    }

    public User getReceiverUser() {
        return receiverUser;
    }

    public void setReceiverUser(User receiverUser) {
        this.receiverUser = receiverUser;
    }

    public Notificationtype getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(Notificationtype notificationType) {
        this.notificationType = notificationType;
    }

    public Boolean getSeenByUser() {
        return seenByUser;
    }

    public void setSeenByUser(Boolean seenByUser) {
        this.seenByUser = seenByUser;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = notificationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Notification that = (Notification) o;

        if (id != that.id) return false;
        if (seenByUser != null ? !seenByUser.equals(that.seenByUser) : that.seenByUser != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (seenByUser != null ? seenByUser.hashCode() : 0);
        return result;
    }
}
