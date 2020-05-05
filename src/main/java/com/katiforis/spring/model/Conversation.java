package com.katiforis.spring.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="conversation")
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "isDeleted", nullable = false)
    private boolean isDeleted;

    @OneToMany(mappedBy = "conversation", fetch = FetchType.LAZY)
    protected Set<ChatMessage> chatMessages;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_conversation",
            joinColumns = { @JoinColumn(name = "conversationId") },
            inverseJoinColumns = { @JoinColumn(name = "userId") })
    protected Set<User> users = new HashSet<User>();

    public Conversation() {}
    public Conversation(Set<User> users) {
        this.isDeleted = false;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Set<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(Set<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conversation that = (Conversation) o;

        if (id != that.id) return false;
        if (isDeleted != that.isDeleted) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (isDeleted ? 1 : 0);
        return result;
    }
}
