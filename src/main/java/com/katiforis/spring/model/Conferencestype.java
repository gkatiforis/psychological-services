package com.katiforis.spring.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "conferences_type")
public class Conferencestype {

    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "type", nullable = true, length = 50)
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Conferencestype(){}
    public Conferencestype(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conferencestype that = (Conferencestype) o;

        if (id != that.id) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
