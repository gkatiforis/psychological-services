package com.katiforis.spring.model;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "working_hour")
public class WorkingHour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "dayId", nullable = false)
    private int dayId;
    @Basic
    @Column(name = "working_time", nullable = false)
    private Time workingTime;

    public WorkingHour() {}
    public WorkingHour(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }


    public Time getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(Time workingTime) {
        this.workingTime = workingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkingHour that = (WorkingHour) o;

        if (id != that.id) return false;
        if (dayId != that.dayId) return false;
        if (workingTime != null ? !workingTime.equals(that.workingTime) : that.workingTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + dayId;
        result = 31 * result + (workingTime != null ? workingTime.hashCode() : 0);
        return result;
    }
}
