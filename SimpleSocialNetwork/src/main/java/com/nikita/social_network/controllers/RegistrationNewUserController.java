package com.nikita.social_network.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "RegistrationNewUserController")
public class RegistrationNewUserController {

    @RequestMapping(method = RequestMethod.POST)
    public void registrationSubmit(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        System.out.println(name+" "+email+" "+password);
    }

}
