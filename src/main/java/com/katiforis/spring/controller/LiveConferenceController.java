package com.katiforis.spring.controller;

import com.katiforis.spring.model.User;
import com.katiforis.spring.service.interfaces.ConferenceService;
import com.katiforis.spring.service.interfaces.LiveConferenceService;
import com.katiforis.spring.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/liveconference")
public class LiveConferenceController extends BasicController {

    @Autowired
    UserService userService;

    @Autowired
    LiveConferenceService liveConferenceService;

    @RequestMapping(value = {"/isOnline-{conferenceId}" }, method = RequestMethod.POST)
    @ResponseBody
    public Integer isOnline(@PathVariable Integer conferenceId) {

        User loggedinuser = userService.findBySSO(getPrincipal());
       // Conference conference = conferenceService.findConferencesById(conferenceId);

        Integer isOnline = liveConferenceService.isOnline(loggedinuser.getId(), conferenceId);

        return isOnline;

    }
}
