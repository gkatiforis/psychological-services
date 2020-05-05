package com.katiforis.spring.dao;

import com.katiforis.spring.model.Service;
import com.katiforis.spring.model.Therapist;
import com.katiforis.spring.model.WorkingHour;

import java.util.List;


public interface ServiceDao {
    Service findById(int id);
    void save(Service service);
    void update(Service service);
    void saveOrUpdate(Service service);
    List<Service> findTherapistServicies(Integer userId);
    List<Service> findAllService();
}