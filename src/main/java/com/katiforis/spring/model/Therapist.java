package com.katiforis.spring.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name="therapist")
@PrimaryKeyJoinColumn(name="id")
public class Therapist  extends User  implements Serializable{

    @Column(name = "specialisation")
    private String specialisation;

    @Column(name = "exp")
    private String exp;
    @Column(name = "bio")
    private String bio;
    @Column(name = "price_per_hour")
    private BigDecimal pricePerHour;

    @Column(name = "videoCharge", precision = 2)
    private BigDecimal videoCharge;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "therapist_specialite",
            joinColumns = { @JoinColumn(name = "therapistId") },
            inverseJoinColumns = { @JoinColumn(name = "specialiteId") })
    private Set<Specialite> specialities = new HashSet<Specialite>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "working_hour_therapist",
            joinColumns = { @JoinColumn(name = "therapistId") },
            inverseJoinColumns = { @JoinColumn(name = "working_hourId") })
    private Set<WorkingHour> workingHours = new HashSet<WorkingHour>();

    @OneToMany(mappedBy = "therapist", fetch = FetchType.LAZY)
    private Set<Service> servicies = new HashSet<Service>();

    @Basic
    @Column(name = "yearsExp", nullable = true)
    Integer yearsExp;

    public Therapist(){}

    public Set<WorkingHour> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Set<WorkingHour> workingHours) {
        this.workingHours = workingHours;
    }

    @Basic
    @Column(name = "videoCharge", nullable = true, precision = 2)
    public BigDecimal getVideoCharge() {
        return videoCharge;
    }

    public void setVideoCharge(BigDecimal videoCharge) {
        this.videoCharge = videoCharge;
    }

    public Set<Specialite> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Specialite> specialities) {
        this.specialities = specialities;
    }

    public Set<Service> getServicies() {
        return servicies;
    }

    public void setServicies(Set<Service> servicies) {
        this.servicies = servicies;
    }


    @OneToMany(mappedBy = "therapist", fetch = FetchType.LAZY)
    private Set<Conference> conferences;

    @OneToMany(mappedBy = "therapist", fetch = FetchType.LAZY)
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
    @Basic
    @Column(name = "exp", nullable = true, length = 5000)
    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    @Basic
    @Column(name = "bio", nullable = true, length = 5000)
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Basic
    @Column(name = "specialisation", nullable = true, length = 200)
    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 5000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "price_per_hour", nullable = true, precision = 2)
    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Therapist therapist = (Therapist) o;

        if (specialisation != null ? !specialisation.equals(therapist.specialisation) : therapist.specialisation != null)
            return false;
        if (description != null ? !description.equals(therapist.description) : therapist.description != null)
            return false;
        if (pricePerHour != null ? !pricePerHour.equals(therapist.pricePerHour) : therapist.pricePerHour != null)
            return false;
        if (!Arrays.equals(image, therapist.image)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;

        result = 31 * result + (specialisation != null ? specialisation.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (pricePerHour != null ? pricePerHour.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
