package com.katiforis.spring.dao;

import com.katiforis.spring.model.Conference;
import com.katiforis.spring.model.Therapist;

import java.util.Date;
import java.util.List;


public interface ConferenceDao {
    Conference findById(int id);
    Conference findByIdFull(int id);
    void save(Conference conference);
    void update(Conference conference);
    void saveOrUpdate(Conference conference);
    List<Conference> findConferenceByCritiria(Integer userId, Integer conferencestatus);
    List<Conference> findConferenceCountPerDay(Integer userId, Date date );
}