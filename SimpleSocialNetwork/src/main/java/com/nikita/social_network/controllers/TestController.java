package com.nikita.social_network.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by nikita on 14.09.2016.
 */
@Controller
@RequestMapping(value = "TestController")
public class TestController {

    @RequestMapping(value = "test", method = RequestMethod.GET)
    @ResponseBody
    public String showIndex() {
        return "Hello world hey hey!";
    }

    @RequestMapping(value = "homePage", method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView result = new ModelAndView("home");
        result.addObject("name","my name");
        return result;
    }

}