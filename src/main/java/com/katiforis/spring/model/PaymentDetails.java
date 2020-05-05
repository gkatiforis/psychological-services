package com.katiforis.spring.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "payment_details")
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "profitPercentage", nullable = false, precision = 2)
    private BigDecimal profitPercentage;
    @Basic
    @Column(name = "videoCostPerMinute", nullable = false, precision = 3)
    private BigDecimal videoCostPerMinute;

    @Basic
    @Column(name = "isEnabled", nullable = true)
    private Boolean isEnabled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public BigDecimal getProfitPercentage() {
        return profitPercentage;
    }

    public void setProfitPercentage(BigDecimal profitPercentage) {
        this.profitPercentage = profitPercentage;
    }


    public BigDecimal getVideoCostPerMinute() {
        return videoCostPerMinute;
    }

    public void setVideoCostPerMinute(BigDecimal videoCostPerMinute) {
        this.videoCostPerMinute = videoCostPerMinute;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentDetails that = (PaymentDetails) o;

        if (id != that.id) return false;
        if (profitPercentage != null ? !profitPercentage.equals(that.profitPercentage) : that.profitPercentage != null)
            return false;
        if (videoCostPerMinute != null ? !videoCostPerMinute.equals(that.videoCostPerMinute) : that.videoCostPerMinute != null)
            return false;
        if (isEnabled != null ? !isEnabled.equals(that.isEnabled) : that.isEnabled != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (profitPercentage != null ? profitPercentage.hashCode() : 0);
        result = 31 * result + (videoCostPerMinute != null ? videoCostPerMinute.hashCode() : 0);
        result = 31 * result + (isEnabled != null ? isEnabled.hashCode() : 0);
        return result;
    }
}
