package com.nikita.social_network.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "EntryNewUserController")
public class EntryNewUserController {
    @RequestMapping(method = RequestMethod.POST)
    public void entrySubmit(@RequestParam String email, @RequestParam String password) {
        System.out.println(email+" "+password);
    }
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView entryPage(){
        ModelAndView result=new ModelAndView("entry1");
        return result;
    }
}
