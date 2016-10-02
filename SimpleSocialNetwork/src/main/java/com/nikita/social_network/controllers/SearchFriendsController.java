package com.nikita.social_network.controllers;

import com.nikita.social_network.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "SearchFriendsController")
public class SearchFriendsController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView searchFriends(@RequestParam(required = false) String filter) {
        ModelAndView result = new ModelAndView("search_friends");
        result.addObject("filter", filter);
        List<User> usersResult = null;
        result.addObject("users", usersResult);
        //в юзердао создать метод для поиска пользователей по полям имя и маил
        return result;
    }
}
