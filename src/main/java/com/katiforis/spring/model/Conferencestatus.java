package com.katiforis.spring.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "conference_status" )
public class Conferencestatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "descr", nullable = true, length = 30)
    private String descr;


    public Conferencestatus(){}
    public Conferencestatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conferencestatus that = (Conferencestatus) o;

        if (id != that.id) return false;
        if (descr != null ? !descr.equals(that.descr) : that.descr != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (descr != null ? descr.hashCode() : 0);
        return result;
    }
}
