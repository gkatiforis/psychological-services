package com.katiforis.spring.service.interfaces;

public interface LiveConferenceService {
    String initConference(Integer userId, Integer conferenceId);
    Integer isOnline(Integer userId, Integer conferenceId);
    String getTwilioToken(String username, String roomName);
}
