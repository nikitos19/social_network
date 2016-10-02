package com.nikita.social_network.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "HomePageController")
    public class HomePageController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView result = new ModelAndView("home");
        return result;
    }
}
