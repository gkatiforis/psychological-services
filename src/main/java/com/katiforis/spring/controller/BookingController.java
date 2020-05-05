package com.katiforis.spring.controller;

import com.katiforis.spring.model.User;
import com.katiforis.spring.service.interfaces.AdvisedPersonService;
import com.katiforis.spring.service.interfaces.ConferenceService;
import com.katiforis.spring.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;

@Controller
@RequestMapping("/booking")
public class BookingController extends BasicController {

    @Autowired
    UserService userService;

    @Autowired
    AdvisedPersonService advisedPersonService;

    @Autowired
    ConferenceService conferenceService;

    @RequestMapping(value = "/bookConference-{userId}-{therapistId}-{date}-{workingHourId}-{descr}-{typeId}-{orderId}", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<?> bookConference(@PathVariable(value = "userId") Integer userId,
                                            @PathVariable(value = "therapistId") Integer therapistId,
                                            @PathVariable(value = "date") @DateTimeFormat(pattern = "yyyyMMdd") Date date,
                                            @PathVariable(value = "workingHourId") Integer workingHourId,
                                            @PathVariable(value = "descr") String descr,
                                            @PathVariable(value = "typeId") Integer type,
                                            @PathVariable(value = "orderId") Integer orderId,
                                            UriComponentsBuilder b) {
        java.sql.Date sqldate = new java.sql.Date(date.getTime());

        conferenceService.bookConference(userId, therapistId, sqldate, workingHourId, descr, type, orderId);

        UriComponents uriComponents = b.path("/myConferences").build();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.OK);


        //   return "ok";
    }

    @RequestMapping(value = "/buyService-{serviceId}", method = {RequestMethod.POST})
    @ResponseBody
    public String buyService(@PathVariable(value = "serviceId") Integer serviceId) {

        User loggedinuser = userService.findBySSO(getPrincipal());
        advisedPersonService.buyService(loggedinuser.getId(), serviceId);

        return "ok";
    }
}
