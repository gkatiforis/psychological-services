package com.katiforis.spring.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="service_type")
public class ServiceType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "title", nullable = false, length = 500)
    private String title;
    @Basic
    @Column(name = "isForMultipleConferences", nullable = true)
    private Boolean isForMultipleConferences;

    public ServiceType(){}
    public ServiceType(int id){this.id = id;}
    public ServiceType(String title, Boolean isForMultipleConferences) {
        this.title = title;
        this.isForMultipleConferences = isForMultipleConferences;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getForMultipleConferences() {
        return isForMultipleConferences;
    }

    public void setForMultipleConferences(Boolean forMultipleConferences) {
        isForMultipleConferences = forMultipleConferences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceType that = (ServiceType) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (isForMultipleConferences != null ? !isForMultipleConferences.equals(that.isForMultipleConferences) : that.isForMultipleConferences != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (isForMultipleConferences != null ? isForMultipleConferences.hashCode() : 0);
        return result;
    }
}
