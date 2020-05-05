package com.katiforis.spring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advisedPersonId", nullable = false)
    private AdvisedPerson advisedPerson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "therapistId", nullable = false)
    private Therapist therapist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serviceId", nullable = false)
    private Service service;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
//    @JsonBackReference
    private Set<Conference> conferences;

    @Column(name = "conferenceCountUsed", nullable = true)
    private Integer conferenceCountUsed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderStatus", nullable = false)
    private Orderstatus orderStatus;

    public Order(){}
    public Order(AdvisedPerson advisedPerson, Therapist therapist, Service service, Integer conferenceCountUsed, Orderstatus orderStatus) {
        this.advisedPerson = advisedPerson;
        this.therapist = therapist;
        this.service = service;
        this.conferenceCountUsed = conferenceCountUsed;
        this.orderStatus = orderStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Conference> getConferences() {
        return conferences;
    }

    public void setConferences(Set<Conference> conferences) {
        this.conferences = conferences;
    }

    public AdvisedPerson getAdvisedPerson() {
        return advisedPerson;
    }

    public void setAdvisedPerson(AdvisedPerson advisedPerson) {
        this.advisedPerson = advisedPerson;
    }

    public Therapist getTherapist() {
        return therapist;
    }

    public void setTherapist(Therapist therapist) {
        this.therapist = therapist;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Integer getConferenceCountUsed() {
        return conferenceCountUsed;
    }

    public void setConferenceCountUsed(Integer conferenceCountUsed) {
        this.conferenceCountUsed = conferenceCountUsed;
    }

    public Orderstatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Orderstatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (advisedPerson != order.advisedPerson) return false;
        if (therapist != order.therapist) return false;
        if (service != order.service) return false;
        if (conferenceCountUsed != null ? !conferenceCountUsed.equals(order.conferenceCountUsed) : order.conferenceCountUsed != null)
            return false;


        return true;
    }
}
