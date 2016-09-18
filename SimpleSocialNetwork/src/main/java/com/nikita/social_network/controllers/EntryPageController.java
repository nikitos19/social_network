package com.nikita.social_network.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "EntryPageController")
public class EntryPageController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView entryPage(){
        ModelAndView result = new ModelAndView("entry");
        return result;
    }
}
