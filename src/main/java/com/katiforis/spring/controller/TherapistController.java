package com.katiforis.spring.controller;

import com.katiforis.spring.model.*;
import com.katiforis.spring.service.interfaces.TherapistService;
import com.katiforis.spring.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/therapist")
public class TherapistController extends BasicController {

    @Autowired
    TherapistService therapistService;

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/getExpertProfileData-{userId}" }, method = RequestMethod.GET)
    @ResponseBody
    public Therapist getExpertProfileData(@PathVariable Integer userId) {

        Therapist therapist = therapistService.findByTherapistId(userId);
        return therapist;
    }

    @RequestMapping(value = {"/getTherapistFreeWorkingHourInDay-{userId}-{date}" }, method = RequestMethod.GET)
    @ResponseBody
    public List<WorkingHour> getTherapistFreeWorkingHourInDay(@PathVariable Integer userId,
                                                              @PathVariable @DateTimeFormat(pattern = "yyyyMMdd")
                                                                      Date date , ModelMap model) {
        List<WorkingHour> freeWorkingHours = therapistService.getTherapistFreeWorkingHourInDay(userId, date);

        return freeWorkingHours;

    }

    @RequestMapping(value = {"/getTherapistFreeWorkingHourPerDayCountInMonth-{userId}" }, method = RequestMethod.GET)
    @ResponseBody
    public List<Integer> getTherapistFreeWorkingHourPerDayCountInMonth(@PathVariable Integer userId) {
        List<Integer> freeWorkingHours = therapistService.getTherapistFreeWorkingHourPerDayCountInMonth(userId);
        return freeWorkingHours;

    }

    @RequestMapping(value = "/saveExpertProfile", method = RequestMethod.POST)
    @ResponseBody
    public Therapist saveExpertProfile(@ModelAttribute Therapist therapist,
                                       BindingResult result) {
        therapistService.updateTherapist(therapist);

        return therapist;
    }

    @RequestMapping(value = "/saveService", method = RequestMethod.POST)
    @ResponseBody
    public Service saveService(@RequestParam("serviceType") Integer serviceType, @ModelAttribute Service service,
                               BindingResult result) {

        User loggedinuser = userService.findBySSO(getPrincipal());
        Therapist therapist = therapistService.findByTherapistId(loggedinuser.getId());
        service.setTherapist(therapist);
        service.setEnabled(true);
        service.setDeleted(false);
        service.setServiceType(new ServiceType(serviceType));
        if(service.getConferenceCount()==null) service.setConferenceCount(1);
        if(service.getSpecialOffer()==null) service.setSpecialOffer(false);
        therapistService.saveService(service);

        return service;
    }

    @RequestMapping(value = {"/removeService-{serviceId}" }, method = RequestMethod.GET)
    @ResponseBody
    public String removeService( @PathVariable  Integer serviceId) {

        therapistService.removeServiceFromTherapist(serviceId);
        return "ok";
    }

    @RequestMapping(value = {"/searchTherapistByCriteria-{disorderIds}" }, method = RequestMethod.GET)
    @ResponseBody
    public List<Therapist> searchTherapistByCritiria(@PathVariable  List<Integer> disorderIds) {

        List<Therapist> therapists = therapistService.findTherapistsByCritiria(disorderIds);

        return therapists;
    }

    @RequestMapping(value = {"/getSpec-{userId}-{have}" }, method = RequestMethod.GET)
    @ResponseBody
    public List<Specialite> getSpecHave(@PathVariable Integer userId, @PathVariable Integer have) {
        return  therapistService.getSpecialiteHave(userId, have);
    }

    @RequestMapping(value = {"/getSpec" }, method = RequestMethod.GET)
    @ResponseBody
    public List<Specialite> getSpec() {
        return therapistService.findAllSpecialite();
    }

    @RequestMapping(value = {"/addSpec-{userId}-{specIds}" }, method = RequestMethod.GET)
    @ResponseBody
    public String addSpec(@PathVariable Integer userId, @PathVariable  List<Integer> specIds) {
        therapistService.addSpecialiteToTherapist(userId, specIds);
        return "ok";
    }
    @RequestMapping(value = {"/removeSpec-{userId}-{specIds}" }, method = RequestMethod.GET)
    @ResponseBody
    public String removeSpec(@PathVariable Integer userId, @PathVariable  List<Integer> specIds) {
        therapistService.removeSpecialiteFromTherapist(userId, specIds);
        return "ok";
    }

    @RequestMapping(value = {"/setWorkingHours-{userId}-{workingHourIds}" }, method = RequestMethod.GET)
    @ResponseBody
    public String addWorkingHours(@PathVariable Integer userId, @PathVariable  List<Integer> workingHourIds) {
        therapistService.setWorkingHoursToTherapist(userId, workingHourIds);
        return "ok";
    }

}
