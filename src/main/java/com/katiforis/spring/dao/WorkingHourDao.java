package com.katiforis.spring.dao;

import com.katiforis.spring.model.WorkingHour;

import java.util.Date;
import java.util.List;

public interface WorkingHourDao {
    WorkingHour findById(int id);
    List<WorkingHour> findAllWorkingHours();
    List<WorkingHour> findTherapistWorkingHours(Integer userId);
    List<WorkingHour> findTherapistFreeWorkingHours(Integer userId, Date date);
}
