package com.katiforis.spring.dao;

import com.katiforis.spring.model.Therapist;

import java.util.List;


public interface TherapistDao {
    Therapist findById(int id);
    void save(Therapist therapist);
    void update(Therapist therapist);
    void saveOrUpdate(Therapist therapist);
    List<Therapist> findAllTherapist();
    List<Therapist> findTherapistByCriteria(List<Integer> skillIds);
}