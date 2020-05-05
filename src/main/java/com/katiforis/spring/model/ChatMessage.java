package com.katiforis.spring.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="chat_message")
public class ChatMessage {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversationId", nullable = false)
    private Conversation conversation;

    @Basic
    @Column(name = "message", nullable = false, length = 500)
    private String message;

    @Basic
    @Column(name = "sendDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendDate;

    @Basic
    @Column(name = "isSeenByUser", nullable = false)
    private boolean isSeenByUser;

    @Basic
    @Column(name = "isDeleted", nullable = false)
    private boolean isDeleted;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSeenByUser() {
        return isSeenByUser;
    }

    public void setSeenByUser(boolean seenByUser) {
        isSeenByUser = seenByUser;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatMessage that = (ChatMessage) o;

        if (id != that.id) return false;
        if (isSeenByUser != that.isSeenByUser) return false;
        if (isDeleted != that.isDeleted) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (sendDate != null ? !sendDate.equals(that.sendDate) : that.sendDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (sendDate != null ? sendDate.hashCode() : 0);
        result = 31 * result + (isSeenByUser ? 1 : 0);
        result = 31 * result + (isDeleted ? 1 : 0);
        return result;
    }
}
