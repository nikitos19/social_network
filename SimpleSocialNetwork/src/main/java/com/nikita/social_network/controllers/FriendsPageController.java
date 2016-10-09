package com.nikita.social_network.controllers;

import com.nikita.social_network.dao.UserDAO;
import com.nikita.social_network.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping(value = "FriendsPageController")
public class FriendsPageController {
    @Autowired
    private UserDAO dao;
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView friendsPage(@RequestParam(required = false) String filter, HttpSession session) throws SQLException {
        List<User> usersResult = null;
        User currentUser = (User) session.getAttribute("user");
        if (filter == null || filter.isEmpty()) {
            usersResult = dao.searchFriends(currentUser.getEmail(),"");
        } else {
            usersResult = dao.searchFriends(currentUser.getEmail(),filter);
        }
        ModelAndView result = new ModelAndView("friends");
        result.addObject("filter", filter);
        result.addObject("users", usersResult);
        return result;
    }
}
