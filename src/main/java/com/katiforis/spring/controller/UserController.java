package com.katiforis.spring.controller;

import com.katiforis.spring.Respones.JsonRespone;
import com.katiforis.spring.Respones.NotificationResponse;
import com.katiforis.spring.model.*;
import com.katiforis.spring.service.interfaces.NotificationService;
import com.katiforis.spring.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController extends BasicController {

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/getDisorders-{userId}-{have}" }, method = RequestMethod.GET)
    @ResponseBody
    public List<Disorder> getDisordersHave(@PathVariable Integer userId, @PathVariable Integer have) {


        return  userService.getDisordersHave(userId, have);
    }

    @RequestMapping(value = {"/getDisorders" }, method = RequestMethod.GET)
    @ResponseBody
    public List<Disorder> getDisorders() {

        //Therapist therapist = therapistService.findByTherapistId(userId);
        ;
        return userService.findAllDisorders();
    }

    @RequestMapping(value = {"/addDisorders-{userId}-{disordersIds}" }, method = RequestMethod.GET)
    @ResponseBody
    public String addDisorders(@PathVariable Integer userId, @PathVariable  List<Integer> disordersIds) {
        userService.addDisordersToUser(userId, disordersIds);
        return "ok";
    }

    @RequestMapping(value = {"/removeDisorders-{userId}-{disordersIds}" }, method = RequestMethod.GET)
    @ResponseBody
    public String removeDisorders(@PathVariable Integer userId, @PathVariable  List<Integer> disordersIds) {
        userService.removeDisordersFromUser(userId, disordersIds);
        return "ok";
    }

    @RequestMapping(value =  "/newuser", method = RequestMethod.POST)
    @ResponseBody
    public JsonRespone saveUser(@ModelAttribute @Valid User user,
                                BindingResult result) {

        JsonRespone respone = null;
        if(result.hasErrors() ){
        }else{
            respone = userService.registerUser(user);
        }
        return respone;
    }

}
