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

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping(value = "UsersPageController")
public class UsersPageController {
    @Autowired
    UserDAO dao;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getUser(@RequestParam(required = false) String filter) throws SQLException {
        List<User> usersResult = getUsersForFilter(filter);
        ModelAndView result = new ModelAndView("all_users");
        result.addObject("filter", filter);
        result.addObject("users", usersResult);
        return result;
    }

    @RequestMapping(value = "updateUser", method = RequestMethod.GET)
    public ModelAndView updateUser(@RequestParam String email, HttpSession session) throws SQLException {
        User currentUser = (User) session.getAttribute("user");
        ModelAndView result = new ModelAndView("all_users");
        List<User> usersResult = getUsersForFilter(null);
        result.addObject("users", usersResult);
        if (currentUser.getEmail().equalsIgnoreCase(email)) {
            result.addObject("error", "you try to remove yourself");
            return result;
        } else {
            dao.updateUser(email);
            ConnectionProvider.getConnection().commit();
            result = new ModelAndView("redirect:/services/UsersPageController");
            return result;
        }
    }

    private List<User> getUsersForFilter(String filter) throws SQLException {
        List<User> usersResult = null;
        if (filter == null || filter.isEmpty()) {
            usersResult = dao.searchUsers("");
        } else {
            usersResult = dao.searchUsers(filter);
        }
        return usersResult;
    }
}
