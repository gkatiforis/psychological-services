package com.katiforis.spring.service.implemetations;

import com.katiforis.spring.dao.*;
import com.katiforis.spring.model.*;
import com.katiforis.spring.service.interfaces.ConferenceService;
import com.katiforis.spring.service.interfaces.LiveConferenceService;
import com.katiforis.spring.service.interfaces.NotificationService;
import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.VideoGrant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;


@Service("liveconferenceService")
@Transactional
public class LiveConferenceServiceImpl implements LiveConferenceService {
    // Substitute your Twilio AccountSid and ApiKey details
    public static final String ACCOUNT_SID = "ACf4ee17a1fc23a03977223e27809717aa";
    public static final String API_KEY_SID = "SKd6d920f9072ff145d784507287cb273e";
    public static final String API_KEY_SECRET = "HoSA5rCYdLXyLff0ZnEVrBIejCEsLndC";

    @Autowired
    private UserDao userDao;

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private NotificationService notificationService;

    public String initConference(Integer userId, Integer conferenceId){
      String token = null;

      User user = userDao.findById(userId);
      Conference conference = conferenceService.findById(conferenceId);
      if(conference != null){
          if(conference.getAdvisedPerson().getId() == user.getId() ||
                  conference.getTherapist().getId() == user.getId()){

              token = getTwilioToken(user.getSsoId(), conference.getRoomName());
          }

          if(conference.getAdvisedPerson().getId() == user.getId()){
              notificationService.notifStartConference(user, conference.getTherapist());
          }else if(conference.getTherapist().getId() == user.getId()){
              notificationService.notifStartConference(user, conference.getAdvisedPerson());
          }

      }
      return token;
    }

    public Integer isOnline(Integer userId, Integer conferenceId){
        User user = userDao.findById(userId);
        Conference conference = conferenceService.findById(conferenceId);

        final int timediffInSeconds = 5;

        Integer isOnline = new Integer(0);

        Date now = new Date(Calendar.getInstance().getTime().getTime());

        if(conference != null){

            if(conference.getAdvisedPerson().getId() == user.getId()){
                long secondsDiff = getdateDiffInSeconds(conference.getExpertJoinedDate(), now);
                if( secondsDiff <= timediffInSeconds && secondsDiff >=0){
                   isOnline = 1;
                }
                conference.setPersonJoinedDate(now);

            }else if(conference.getTherapist().getId() == user.getId()){
                long secondsDiff = getdateDiffInSeconds(conference.getPersonJoinedDate(), now);
                if( secondsDiff <= timediffInSeconds && secondsDiff >=0){
                    isOnline = 1;
                }
                conference.setExpertJoinedDate(now);
            }

          conferenceService.updateConference(conference);
        }

        return isOnline;
    }

    public long getdateDiffInSeconds(Date d1, Date d2){
        if(d1 == null || d2 == null) return -1;
        long seconds = (d2.getTime()-d1.getTime())/1000;
        return seconds;
    }

    public String getTwilioToken(String username, String roomName){
        // Create a VideoGrant
        final VideoGrant grant = new VideoGrant();
        grant.setRoom(roomName);

        // Create an Access Token
        final AccessToken token = new AccessToken.Builder(ACCOUNT_SID, API_KEY_SID, API_KEY_SECRET)
                .identity(username) // Set the Identity of this token
                .grant(grant) // Grant access to Video
                .build();

        // Serialize the token as a JWT
        final String jwt = token.toJwt();
        // System.out.println(jwt);
        return jwt;
    }
}
