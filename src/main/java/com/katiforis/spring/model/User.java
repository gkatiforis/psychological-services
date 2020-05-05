package com.katiforis.spring.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="app_user")
@Inheritance(strategy=InheritanceType.JOINED)
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected  Integer id;

    @NotEmpty
    @Column(name="SSO_ID")
    protected String ssoId;

    @NotEmpty
    @Column(name="PASSWORD", nullable=false)
    protected String password;

    @NotEmpty
    @Column(name="EMAIL", unique = true, nullable=false)
    protected String email;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "APP_USER_USER_PROFILE",
            joinColumns = { @JoinColumn(name = "USER_ID") },
            inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
    protected Set<UserProfile> userProfiles = new HashSet<UserProfile>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_conversation",
            joinColumns = { @JoinColumn(name = "userId") },
            inverseJoinColumns = { @JoinColumn(name = "conversationId") })
    protected Set<Conversation> conversations = new HashSet<Conversation>();


    @OneToMany(mappedBy = "senderUser", fetch = FetchType.LAZY)
    protected Set<Notification> sendNotificationd;

    @OneToMany(mappedBy = "receiverUser", fetch = FetchType.LAZY)
    protected Set<Notification> receiveNotifications;

    @Column(name = "first_name")
    protected String first_name;
    @Column(name = "last_name")
    protected String last_name;
    @Column(name = "phone")
    protected String phone;

    @Column(name = "description")
    protected String description;

    @Column(name = "image",columnDefinition="mediumblob")
    protected byte[] image;
    @Column(name = "birthday", nullable = true)
    protected Timestamp birthday;

    @Basic
    @Column(name = "amount", nullable = true, precision = 2)
    BigDecimal amount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_disorder",
            joinColumns = { @JoinColumn(name = "userId") },
            inverseJoinColumns = { @JoinColumn(name = "disorderId") })
    private Set<Disorder> disorders = new HashSet<Disorder>();

    public Set<Conversation> getConversations() {
        return conversations;
    }

    public void setConversations(Set<Conversation> conversations) {
        this.conversations = conversations;
    }

    public Set<Notification> getSendNotificationd() {
        return sendNotificationd;
    }

    public void setSendNotificationd(Set<Notification> sendNotificationd) {
        this.sendNotificationd = sendNotificationd;
    }

    public Set<Notification> getReceiveNotifications() {
        return receiveNotifications;
    }

    public void setReceiveNotifications(Set<Notification> receiveNotifications) {
        this.receiveNotifications = receiveNotifications;
    }

    public User(){}
    public User(int id){this.id = id;}
    public User( String ssoId, String password, String email, Set<UserProfile> userProfiles) {
        this.ssoId = ssoId;
        this.password = password;
        this.email = email;
        this.userProfiles = userProfiles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSsoId() {
        return ssoId;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Set<Disorder> getDisorders() {
        return disorders;
    }

    public void setDisorders(Set<Disorder> disorders) {
        this.disorders = disorders;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (ssoId == null) {
            if (other.ssoId != null)
                return false;
        } else if (!ssoId.equals(other.ssoId))
            return false;
        return true;
    }

    /*
     * DO-NOT-INCLUDE passwords in toString function.
     * It is done here just for convenience purpose.
     */
    @Override
    public String toString() {
        return "User [id=" + id + ", ssoId=" + ssoId + ", password=" + password
                + ", email=" + email + "]";
    }
}