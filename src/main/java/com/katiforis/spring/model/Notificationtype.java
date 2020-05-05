package com.katiforis.spring.model;

import javax.persistence.*;

@Entity
@Table(name = "notification_type")
public class Notificationtype {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    @Column(name = "notificationText", nullable = true, length = 200)
    private String notificationText;
    @Basic
    @Column(name = "url", nullable = true, length = 200)
    private String url;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Notificationtype(){}
    public Notificationtype(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Notificationtype that = (Notificationtype) o;

        if (id != that.id) return false;
        if (notificationText != null ? !notificationText.equals(that.notificationText) : that.notificationText != null)
            return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (notificationText != null ? notificationText.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
