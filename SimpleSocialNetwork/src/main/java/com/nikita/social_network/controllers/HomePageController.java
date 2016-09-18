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
    private User getCurrentUser() {
        User user = new User();
        user.setName("Nikita");
        Calendar clnd = Calendar.getInstance();
        clnd.set(1900 + 1995, 02, 19);
        user.setBirthday(clnd.getTime());
        user.setCity(new City(1, "Spb"));
        return user;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView result = new ModelAndView("home");
        User u = getCurrentUser();
        result.addObject("user", u);
        String birthday = u.getBirthday().getYear() + " " + u.getBirthday().getMonth() + " " + u.getBirthday().getDate();
        result.addObject("birthday", birthday);
        result.addObject("city",getCurrentUser().getCity());
        return result;
    }
}
