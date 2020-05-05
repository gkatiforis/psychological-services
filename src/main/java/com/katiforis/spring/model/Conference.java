package com.katiforis.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;



import java.util.Date;
import java.util.Set;

@Entity
@Table(name ="conference")
public class Conference {
    @Id
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name  = "title", nullable = false)
    private String title;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "date", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "time", nullable = true)
    @Temporal(TemporalType.TIME)
    private Date time;

    @Column(name = "userDesc", nullable = true, length = 500)
    private String userDesc;

    @Basic
    @Column(name = "roomName", nullable = false, length = 100)
    private String roomName;

    @Basic
    @Column(name = "expertJoinedDate", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date expertJoinedDate;

    @Basic
    @Column(name = "personJoinedDate", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date personJoinedDate;

    @Basic
    @Column(name = "completedDate", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date completedDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expertId", nullable = false)
    private Therapist therapist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advisedPersonID", nullable = false)
    private AdvisedPerson advisedPerson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", nullable = false)
    @JsonIgnore
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workingHourId", nullable = false)
    private WorkingHour workingHour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conferencesTypeId", nullable = false)
    private Conferencestype conferencesType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status", nullable = false)
    private Conferencestatus conferencestatus;

    @Transient
    private String dateWhen;

    public Conference(){}
    public Conference(Date date, Date time, String title, String userDesc, String roomName, Therapist therapist,
                      AdvisedPerson advisedPerson, WorkingHour workingHour, Conferencestype conferencesType,
                      Conferencestatus conferencestatus, Order order) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.title = title;
        this.userDesc = userDesc;
        this.roomName = roomName;
        this.expertJoinedDate = expertJoinedDate;
        this.personJoinedDate = personJoinedDate;
        this.completedDate = completedDate;
        this.therapist = therapist;
        this.advisedPerson = advisedPerson;
        this.workingHour = workingHour;
        this.conferencesType = conferencesType;
        this.conferencestatus = conferencestatus;
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public WorkingHour getWorkingHour() {
        return workingHour;
    }

    public void setWorkingHour(WorkingHour workingHour) {
        this.workingHour = workingHour;
    }

    public Therapist getTherapist() {
        return therapist;
    }

    public void setTherapist(Therapist therapist) {
        this.therapist = therapist;
    }

    public AdvisedPerson getAdvisedPerson() {
        return advisedPerson;
    }

    public void setAdvisedPerson(AdvisedPerson advisedPerson) {
        this.advisedPerson = advisedPerson;
    }

    public Conferencestatus getConferencestatus() {
        return conferencestatus;
    }

    public void setConferencestatus(Conferencestatus conferencestatus) {
        this.conferencestatus = conferencestatus;
    }

    public Conferencestype getConferencesType() {
        return conferencesType;
    }

    public void setConferencesType(Conferencestype conferencesType) {
        this.conferencesType = conferencesType;
    }

    public String getDateWhen() {
        return dateWhen;
    }

    public void setDateWhen(String dateWhen) {
        this.dateWhen = dateWhen;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public java.util.Date getExpertJoinedDate() {
        return expertJoinedDate;
    }

    public void setExpertJoinedDate(Date expertJoinedDate) {
        this.expertJoinedDate = expertJoinedDate;
    }

    public java.util.Date getPersonJoinedDate() {
        return personJoinedDate;
    }

    public void setPersonJoinedDate(Date personJoinedDate) {
        this.personJoinedDate = personJoinedDate;
    }

    public java.util.Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conference that = (Conference) o;

        if (id != that.id) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (userDesc != null ? !userDesc.equals(that.userDesc) : that.userDesc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (userDesc != null ? userDesc.hashCode() : 0);
        return result;
    }
}
