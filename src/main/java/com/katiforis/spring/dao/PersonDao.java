package com.katiforis.spring.dao;

import com.katiforis.spring.model.AdvisedPerson;
import com.katiforis.spring.model.Therapist;

import java.util.List;


public interface PersonDao {
    AdvisedPerson findById(int id);
    void save(AdvisedPerson person);
    List<AdvisedPerson> findAllAdvisedPerson();
}