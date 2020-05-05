package com.katiforis.spring.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "service")
public class Service implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "therapistId", nullable = false)
    private Therapist therapist;

    @ManyToOne
    @JoinColumn( name = "serviceTypeId", nullable = false)
    private ServiceType serviceType;

    @Basic
    @Column(name = "descr", nullable = true, length = 1000)
    private String descr;
    @Basic
    @Column(name = "amount", nullable = false, precision = 2)
    private BigDecimal amount;
    @Basic
    @Column(name = "conferenceCount", nullable = false,  columnDefinition = "int default 1")
    private Integer conferenceCount;
    @Basic
    @Column(name = "isSpecialOffer", nullable = false,  columnDefinition = "bit default 0")
    private Boolean isSpecialOffer;
    @Basic
    @Column(name = "isEnabled", nullable = false,  columnDefinition = "bit default 1")
    private Boolean isEnabled;
    @Basic
    @Column(name = "isDeleted", nullable = false, columnDefinition = "bit default 0")
    private Boolean isDeleted;

    public Service(){}

    public Service( String descr, BigDecimal amount, Integer conferenceCount, Boolean isSpecialOffer, ServiceType serviceType) {
        this.serviceType = serviceType;
        this.descr = descr;
        this.amount = amount;
        this.conferenceCount = conferenceCount;
        this.isSpecialOffer = isSpecialOffer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public Integer getConferenceCount() {
        return conferenceCount;
    }

    public void setConferenceCount(Integer conferenceCount) {
        this.conferenceCount = conferenceCount;
    }


    public Boolean getSpecialOffer() {
        return isSpecialOffer;
    }

    public void setSpecialOffer(Boolean specialOffer) {
        isSpecialOffer = specialOffer;
    }


    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }


    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Therapist getTherapist() {
        return therapist;
    }

    public void setTherapist(Therapist therapist) {
        this.therapist = therapist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Service service = (Service) o;

        if (id != service.id) return false;
        if (therapist != service.therapist) return false;
        if (descr != null ? !descr.equals(service.descr) : service.descr != null) return false;
        if (amount != null ? !amount.equals(service.amount) : service.amount != null) return false;
        if (conferenceCount != null ? !conferenceCount.equals(service.conferenceCount) : service.conferenceCount != null)
            return false;
        if (isSpecialOffer != null ? !isSpecialOffer.equals(service.isSpecialOffer) : service.isSpecialOffer != null)
            return false;
        if (isEnabled != null ? !isEnabled.equals(service.isEnabled) : service.isEnabled != null) return false;
        if (isDeleted != null ? !isDeleted.equals(service.isDeleted) : service.isDeleted != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (descr != null ? descr.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (conferenceCount != null ? conferenceCount.hashCode() : 0);
        result = 31 * result + (isSpecialOffer != null ? isSpecialOffer.hashCode() : 0);
        result = 31 * result + (isEnabled != null ? isEnabled.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        return result;
    }
}
