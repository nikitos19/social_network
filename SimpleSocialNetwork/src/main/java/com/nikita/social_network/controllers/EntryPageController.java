package com.nikita.social_network.controllers;

import com.nikita.social_network.ConnectionProvider;
import com.nikita.social_network.dao.UserDAO;
import com.nikita.social_network.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;


@Controller
@RequestMapping(value = "EntryPageController")
public class EntryPageController {
    @Autowired
    private UserDAO dao;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView entryPage() {
        ModelAndView result = new ModelAndView("entry");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView entrySubmit(@RequestParam String email, @RequestParam String password, HttpServletRequest req) throws SQLException, UnsupportedEncodingException {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            ModelAndView result = new ModelAndView("entry");
            result.addObject("error", "Something bad happen. Please write to administrator of this resource");
            return result;
        }
        messageDigest.update(password.getBytes("UTF-8"));
        byte[] digest = messageDigest.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < digest.length; i++) {
            sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
        }
        User u = dao.getUser(email, sb.toString());
        System.out.println(email + " " + password);

        if (u == null) {
            ModelAndView result = new ModelAndView("entry");
            result.addObject("error", "User not found");
            return result;
        }
        ModelAndView result = new ModelAndView("redirect:/services/HomePageController");
        req.getSession().setAttribute("user", u);
        return result;
    }

}
