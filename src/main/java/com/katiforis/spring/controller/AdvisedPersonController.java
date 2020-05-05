package com.katiforis.spring.controller;

import com.katiforis.spring.model.*;
import com.katiforis.spring.service.interfaces.AdvisedPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/advisedPerson")
public class AdvisedPersonController extends BasicController {

    @Autowired
    AdvisedPersonService advisedPersonService;


    @RequestMapping(value = "/saveAdvisedPersonProfile", method = RequestMethod.POST)
    @ResponseBody
    public AdvisedPerson saveAdvisedPersonProfile(@ModelAttribute AdvisedPerson advisedPerson,
                                       BindingResult result) {
        advisedPersonService.updateAdvisedPerson(advisedPerson);
        return advisedPerson;
    }
}
