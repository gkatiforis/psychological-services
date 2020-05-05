package com.katiforis.spring.service.interfaces;

import com.katiforis.spring.model.*;

import java.util.Date;
import java.util.List;

public interface ConferenceService {
    Conference findById(Integer conferenceId);
    void saveConference(Conference conference);
    void updateConference(Conference conference);
    Conference findConferencesByIdFull(Integer conferenceId);
    Integer setConferenceCompleted(Integer userId, Integer conferenceId);
    Integer setConferenceCanceled(Integer userId, Integer conferenceId);
    Integer cancelOrder(Integer userId, Integer orderId);
    void bookConference(Integer userId, Integer therapistId, Date date, Integer workingHourId, String descr, Integer type, Integer orderId);
    List<Order> findConferencesByCritiria(Integer userId, Integer conferencestatus);
    List<Integer> getConferenceCountPerDayInMounth(Integer userId);
}
