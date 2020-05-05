package com.katiforis.spring.controller;

import com.katiforis.spring.model.*;
import com.katiforis.spring.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/")
public class AppController  extends BasicController{

    @Autowired
    UserService userService;

    @Autowired
    AdvisedPersonService advisedPersonService;

    @Autowired
    TherapistService therapistService;

    @Autowired
    ConferenceService conferenceService;

    @Autowired
    LiveConferenceService liveConferenceService;

    @Autowired
    ChatService chatService;

    @RequestMapping(value = {"/" }, method = RequestMethod.GET)
    public String testprofile(ModelMap model) {
        return "redirect:/myConferences";
    }

    @RequestMapping(value = {"/chat" }, method = RequestMethod.GET)
    public String chat(ModelMap model) {

        User loggedinuser = userService.findBySSO(getPrincipal());

        List<Conversation> conversations = chatService.getUserConversation(loggedinuser);

        Integer selectedConversationId = 0;
        if(!conversations.isEmpty()) {
            selectedConversationId=conversations.get(0).getId();
        }
        model.addAttribute("loggedinuser", loggedinuser);
        model.addAttribute("selectedConversationId", selectedConversationId);
        model.addAttribute("conversations", conversations);

        return "chat";
    }


    @RequestMapping(value = {"/liveconference-{conferenceId}" }, method = RequestMethod.GET)
    public String liveconference(@PathVariable Integer conferenceId, ModelMap model) {
        User loggedinuser = userService.findBySSO(getPrincipal());
        String token = liveConferenceService.initConference(loggedinuser.getId(), conferenceId);
        if(token == null){
            return "accessDenied";
        }

        Conference conference = conferenceService.findConferencesByIdFull(conferenceId);

        model.addAttribute("conference", conference);
        model.addAttribute("loggedinuser", loggedinuser);
        model.addAttribute("jwt", token);

        return "liveconference";
    }

    @RequestMapping(value = {"/myConferences" }, method = RequestMethod.GET)
    public String myConferences(ModelMap model) {

        User loggedinuser = userService.findBySSO(getPrincipal());
        model.addAttribute("loggedinuser", loggedinuser);

        List<Order> userOrders = userService.findUserOrders(loggedinuser.getId());
        model.addAttribute("userOrders", userOrders);

        return "conferences";
    }


    @RequestMapping(value = {"/search" }, method = RequestMethod.GET)
    public String search(ModelMap model) {

         User loggedinuser = userService.findBySSO(getPrincipal());
         model.addAttribute("loggedinuser", loggedinuser);
        return "search";
    }

    @RequestMapping(value = {  "/myprofile" }, method = RequestMethod.GET)
    public String myprofile(ModelMap model) {


        User currentUser = userService.findBySSO(getPrincipal());

       // model.addAttribute("profileuserId", currentUser.getId());
        model.addAttribute("loggedinuser", currentUser);

        if(currentUser.getUserProfiles().iterator().next().getType().equals("EXPERT")){

            Therapist therapist = therapistService.findByTherapistId(currentUser.getId());
            model.addAttribute("therapist", therapist);

            List<WorkingHour> workingHours = therapistService.findAllWorkingHours();
            model.addAttribute("workingHours", workingHours);

            List<WorkingHour> therapistWorkingHours = therapistService.getTherapistWorkingHour(currentUser.getId());
            model.addAttribute("therapistWorkingHours", therapistWorkingHours);

            List<Service> therapistServicies = therapistService.getTherapistServicies(currentUser.getId());
            model.addAttribute("therapistServicies", therapistServicies);

            List<ServiceType> serviceTypes = therapistService.getServiceTypes();
            model.addAttribute("serviceTypes", serviceTypes);

            return "expertProfile";
        }else if(currentUser.getUserProfiles().iterator().next().getType().equals("PERSON")){
            AdvisedPerson advisedPerson = advisedPersonService.findByAdvisedPersonId(currentUser.getId());
            model.addAttribute("advisedPerson", advisedPerson);
            return "advisedPerson";
        }
        return "admin";
    }

    @RequestMapping(value = {"/profile-{userId}" }, method = RequestMethod.GET)
    public String profile(@PathVariable Integer userId, ModelMap model) {

        User currentUser = userService.findById(userId);
        User loggedinuser = userService.findBySSO(getPrincipal());

        model.addAttribute("loggedinuser", loggedinuser);

        if(currentUser.getUserProfiles().iterator().next().getType().equals("EXPERT")){

            Therapist therapist = therapistService.findByTherapistId(currentUser.getId());
            model.addAttribute("therapist", therapist);

            List<WorkingHour> workingHours = therapistService.findAllWorkingHours();
            model.addAttribute("workingHours", workingHours);

            List<WorkingHour> therapistWorkingHours = therapistService.getTherapistWorkingHour(userId);
            model.addAttribute("therapistWorkingHours", therapistWorkingHours);

            List<Service> therapistServicies = therapistService.getTherapistServicies(currentUser.getId());
            model.addAttribute("therapistServicies", therapistServicies);

            List<ServiceType> serviceTypes = therapistService.getServiceTypes();
            model.addAttribute("serviceTypes", therapistServicies);

            return "expertProfile";
        }else if(currentUser.getUserProfiles().iterator().next().getType().equals("PERSON")){
            AdvisedPerson advisedPerson = advisedPersonService.findByAdvisedPersonId(userId);
            model.addAttribute("advisedPerson", advisedPerson);
            return "advisedPerson";
        }
        return "admin";

    }

    @RequestMapping(value = {"/book-{userId}" }, method = RequestMethod.GET)
    public String book(@PathVariable Integer userId, ModelMap model) {

        User currentUser = userService.findBySSO(getPrincipal());
        Therapist therapist = therapistService.findByTherapistId(userId);
        model.addAttribute("therapist", therapist);
        model.addAttribute("loggedinuser", currentUser);

        List<Service> therapistServicies = therapistService.getTherapistServicies(therapist.getId());
        model.addAttribute("therapistServicies", therapistServicies);

        List<Order> userOrders = advisedPersonService.findUserOrdersByTherapist(currentUser.getId(), therapist.getId());
        model.addAttribute("userOrders", userOrders);

        //  model.addAttribute("loggedinuser", getPrincipal());

        return "book";

    }

    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "accessDenied";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getAuthPage( ModelMap model) {

        if (isCurrentAuthenticationAnonymous()) {
            model.addAttribute("command", "login");
            return "login";
        } else {
            return "redirect:/myprofile";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterPage( ModelMap model) {

        if (isCurrentAuthenticationAnonymous()) {
            model.addAttribute("command", "registration");
            return "login";
        } else {
            return "redirect:/myprofile";
        }
    }
        /**
         * This method handles logout requests.
         * Toggle the handlers if you are RememberMe functionality is useless in your app.
         */
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            //new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }
}
