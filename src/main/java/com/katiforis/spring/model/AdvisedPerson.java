package com.katiforis.spring.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "advised_person")
public class AdvisedPerson extends User {
    public AdvisedPerson(){}
    @OneToMany(mappedBy = "advisedPerson", fetch = FetchType.LAZY)
    private Set<Conference> conferences;

    @OneToMany(mappedBy = "advisedPerson", fetch = FetchType.LAZY)
    private Set<Order> orders = new HashSet<Order>();

    public Set<Conference> getConferences() {
        return conferences;
    }

    public void setConferences(Set<Conference> conferences) {
        this.conferences = conferences;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdvisedPerson that = (AdvisedPerson) o;


        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        return result;
    }
}
