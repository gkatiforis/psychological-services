package com.katiforis.spring.controller;

import com.katiforis.spring.Respones.NotificationResponse;
import com.katiforis.spring.Respones.SubmissionEvent;
import com.katiforis.spring.events.ApplicationEventListener;
import com.katiforis.spring.model.User;
import com.katiforis.spring.service.interfaces.AdvisedPersonService;
import com.katiforis.spring.service.interfaces.ConferenceService;
import com.katiforis.spring.service.interfaces.NotificationService;
import com.katiforis.spring.service.interfaces.UserService;
import com.twilio.rest.notify.v1.service.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("/notification")
public class NotificationController extends BasicController {

    @Autowired
    UserService userService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    ApplicationEventListener applicationEventListener;

    @RequestMapping(value = "/getUserNotifications-{seen}", method = RequestMethod.GET)
    @ResponseBody
    public NotificationResponse getUserNotifications(@PathVariable Integer seen) {

        User user = userService.findBySSO(getPrincipal());
        if(user != null) {
            NotificationResponse notificationResponse = notificationService.getUserNotification(user.getId(), seen);

            return notificationResponse;
        }
        return null;
    }

    @Async
    @RequestMapping("/subcribeForNotifications")
    public SseEmitter getRealTimeMessageAction(
            HttpServletRequest request) {

        User user = userService.findBySSO(getPrincipal());
        SseEmitter sseEmitter = new SseEmitter();
        applicationEventListener.addSseEmitters(user.getId(), sseEmitter);

        return sseEmitter;
    }
}
