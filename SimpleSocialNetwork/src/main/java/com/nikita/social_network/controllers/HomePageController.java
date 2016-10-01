package com.nikita.social_network.controllers;

import com.nikita.social_network.model.City;
import com.nikita.social_network.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;


@Controller
@RequestMapping(value = "HomePageController")
    public class HomePageController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView result = new ModelAndView("home");
        return result;
    }
}
