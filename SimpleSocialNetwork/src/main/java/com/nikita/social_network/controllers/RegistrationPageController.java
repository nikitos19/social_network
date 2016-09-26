package com.nikita.social_network.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "RegistrationPageController")
public class RegistrationPageController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView registrationPage(){
        ModelAndView result = new ModelAndView("registration");
        return result;
    }
}
