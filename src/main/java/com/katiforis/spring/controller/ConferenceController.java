package com.katiforis.spring.controller;

import com.katiforis.spring.model.*;
import com.katiforis.spring.service.interfaces.ConferenceService;
import com.katiforis.spring.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/conference")
public class ConferenceController extends BasicController {

    @Autowired
    UserService userService;

    @Autowired
    ConferenceService conferenceService;

    @RequestMapping(value = {"/getConferenceCountPerDayInMounth" }, method = RequestMethod.GET)
    @ResponseBody
    public List<Integer> getConferenceCountPerDayInMounth() {

        User loggedinuser = userService.findBySSO(getPrincipal());

        List<Integer> counter = conferenceService.getConferenceCountPerDayInMounth(loggedinuser.getId());

        return counter;

    }

    @RequestMapping(value = {"/setConnferenceCompleted-{conferenceId}" }, method = RequestMethod.POST)
    @ResponseBody
    public Integer setConferenceCompleted(@PathVariable Integer conferenceId) {

        User loggedinuser = userService.findBySSO(getPrincipal());

        Integer isCompleted = conferenceService.setConferenceCompleted(loggedinuser.getId(), conferenceId);

        return isCompleted;

    }

    @RequestMapping(value = {"/setConnferenceCanceled-{conferenceId}" }, method = RequestMethod.POST)
    @ResponseBody
    public Integer setConnferenceCanceled(@PathVariable Integer conferenceId) {

        User loggedinuser = userService.findBySSO(getPrincipal());


       Integer isCanceled = conferenceService.setConferenceCanceled(loggedinuser.getId(), conferenceId);


        return isCanceled;

    }

    @RequestMapping(value = "/setOrderCanceled-{orderId}", method = {RequestMethod.POST})
    @ResponseBody
    public Integer setOrderCanceled(@PathVariable(value = "orderId") Integer orderId){

        User loggedinuser = userService.findBySSO(getPrincipal());
        Integer isCanceled = conferenceService.cancelOrder(loggedinuser.getId(), orderId);

        return isCanceled;
    }

    @RequestMapping(value = {"/searchConferenceByCriteria-{userId}-{conferencestatus}" }, method = RequestMethod.GET)
    @ResponseBody
    public List<Order> searchConferenceByCriteria(@PathVariable  Integer userId, @PathVariable  Integer conferencestatus) {

        List<Order> orders = conferenceService.findConferencesByCritiria(userId, conferencestatus);

        return orders;
    }
}
