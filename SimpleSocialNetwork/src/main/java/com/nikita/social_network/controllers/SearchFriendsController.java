package com.nikita.social_network.controllers;

import com.nikita.social_network.dao.UserDAO;
import com.nikita.social_network.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping(value = "SearchFriendsController")
public class SearchFriendsController {
    @Autowired
    private UserDAO dao;
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView searchFriends(@RequestParam(required = false) String filter) throws SQLException {
        List<User> usersResult = null;
        if(filter.isEmpty()){
            usersResult = dao.searchUsers("");
        }
        else {
            usersResult = dao.searchUsers(filter);
        }
        ModelAndView result = new ModelAndView("search_friends");
        result.addObject("filter", filter);
        result.addObject("users", usersResult);
        return result;
    }
}
