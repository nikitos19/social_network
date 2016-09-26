package com.nikita.social_network.controllers;

import com.nikita.social_network.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = "EntryPageController")
public class EntryPageController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView entryPage() {
        ModelAndView result = new ModelAndView("entry");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView entrySubmit(@RequestParam String email, @RequestParam String password, HttpServletRequest req) {
        System.out.println(email + " " + password);
        if (email.equalsIgnoreCase("e@e.e") && password.equalsIgnoreCase("eee")) {
            ModelAndView result = new ModelAndView("entry");
            result.addObject("error", "User not found");
            return result;
        }
        ModelAndView result = new ModelAndView("redirect:HomePageController");
        User user = new User();
        user.setEmail(email);
        user.setName("DonaldDuck");
        req.getSession().setAttribute("user", user);
        return result;
    }

}
