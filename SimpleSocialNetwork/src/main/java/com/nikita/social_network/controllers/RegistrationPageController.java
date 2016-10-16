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

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

@Controller
@RequestMapping(value = "RegistrationPageController")
public class RegistrationPageController {
    @Autowired
    private UserDAO dao;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView registrationPage() {
        ModelAndView result = new ModelAndView("registration");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView registrationSubmit(@RequestParam String name, @RequestParam String email, @RequestParam String password) throws SQLException, UnsupportedEncodingException {
        System.out.println(name + " " + email + " " + password);
        ModelAndView result = new ModelAndView();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes("UTF-8"));
            byte[] digest = messageDigest.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }

            dao.createUser(name, email, sb.toString());
            Connection connection = ConnectionProvider.getConnection();
            connection.commit();
            result.setViewName("redirect:/services/EntryPageController");
            result.addObject("good", "User successfully created");
            return result;
        } catch (UserAlreadyExisted userAlreadyExisted) {
            result.setViewName("registration");
            result.addObject("error", "User already exists");
            return result;
        } catch (NoSuchAlgorithmException e) {
            result.setViewName("registration");
            result.addObject("error", "Something bad happen. Please write to administrator of this resource");
            return result;
        }
    }
}
