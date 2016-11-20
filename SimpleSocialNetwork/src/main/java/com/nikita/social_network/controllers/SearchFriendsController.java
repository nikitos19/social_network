package com.nikita.social_network.controllers;

import com.nikita.social_network.ConnectionProvider;
import com.nikita.social_network.dao.FriendsDAO;
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
@RequestMapping(value = "SearchFriendsController")
public class SearchFriendsController {
    @Autowired
    private FriendsDAO friendsDAO;
    @Autowired
    private UserDAO userDAO;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView searchFriends(@RequestParam(required = false) String filter) throws SQLException {
        List<User> usersResult = getUsersForFilter(filter);
        ModelAndView result = new ModelAndView("search_friends");
        result.addObject("filter", filter);
        result.addObject("users", usersResult);
        return result;
    }

    @RequestMapping(value = "addFriend", method = RequestMethod.GET)
    public ModelAndView addFriend(@RequestParam String email, HttpSession session) throws SQLException {
        User currentUser = (User) session.getAttribute("user");
        ModelAndView result = new ModelAndView("search_friends");
        try {
            List<User> usersResult = getUsersForFilter(null);
            result.addObject("users", usersResult);
            if (currentUser.getEmail().equalsIgnoreCase(email)) {
                result.addObject("error", "you trying to add to friends yourself");
                return result;
            } else {
                friendsDAO.createFriendshipRequest(currentUser.getEmail(), email);
                ConnectionProvider.getConnection().commit();
                result = new ModelAndView("redirect:/services/FriendsPageController");
                return result;
            }
        } catch (SQLException e) {
            result.addObject("error", "friend already added");
            return result;
        }
    }

    private List<User> getUsersForFilter(String filter) throws SQLException {
        List<User> usersResult = null;
        if (filter == null || filter.isEmpty()) {
            usersResult = userDAO.searchUsers("");
        } else {
            usersResult = userDAO.searchUsers(filter);
        }
        return usersResult;
    }
}
