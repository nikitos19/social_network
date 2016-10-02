package com.nikita.social_network.controllers;


import com.nikita.social_network.ConnectionProvider;
import com.nikita.social_network.dao.UserDAO;
import com.nikita.social_network.exceptions.UserAlreadyExisted;
import com.nikita.social_network.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.SQLException;

@Controller
@RequestMapping(value = "RegistrationPageController")
public class RegistrationPageController {
    @Autowired
    private UserDAO dao;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView registrationPage(){
        ModelAndView result = new ModelAndView("registration");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView registrationSubmit(@RequestParam String name, @RequestParam String email, @RequestParam String password) throws SQLException {
        System.out.println(name+" "+email+" "+password);
        try{
            dao.createUser(name, email, password);
            Connection connection = ConnectionProvider.getConnection();
            connection.commit();
            ModelAndView result = new ModelAndView("redirect:/services/EntryPageController");
            result.addObject("good","User successfully created");
            return result;
        }
        catch (UserAlreadyExisted userAlreadyExisted){
            ModelAndView result = new ModelAndView("registration");
            result.addObject("error","User already exists");
            return result;
        }
    }
}
