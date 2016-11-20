package com.nikita.social_network.controllers;

import com.google.common.collect.Lists;
import com.nikita.social_network.ConnectionProvider;
import com.nikita.social_network.dao.FriendsDAO;
import com.nikita.social_network.dao.UserDAO;
import com.nikita.social_network.model.Friendship;
import com.nikita.social_network.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = "FriendsPageController")
public class FriendsPageController {
    @Autowired
    private FriendsDAO dao;
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView friendsPage(@RequestParam(required = false) String filter, HttpSession session) throws SQLException {
        List<User> usersResult = null;
        User currentUser = (User) session.getAttribute("user");
        if (filter == null || filter.isEmpty()) {
            usersResult = dao.searchFriends(currentUser.getEmail(),"");
        } else {
            usersResult = dao.searchFriends(currentUser.getEmail(),filter);
        }
        List<Friendship> friendships = dao.getIAmFriendOf(currentUser.getEmail());
        ModelAndView result = new ModelAndView("friends");
        if(friendships == null ){
            friendships = Collections.emptyList();
        }
        result.addObject("friendships",friendships);
        result.addObject("filter", filter);
        result.addObject("users", usersResult);
        return result;
    }

    @RequestMapping(value = "acceptRequest",method = RequestMethod.PUT)
    public ModelAndView acceptRequest(HttpSession session, @RequestParam String from) throws SQLException {
        User to = (User) session.getAttribute("user");
        dao.acceptFriendRequest(to.getEmail(),from);
        Connection connection = ConnectionProvider.getConnection();
        connection.commit();
        ModelAndView result = new ModelAndView("redirect:/services/FriendsPageController");
        return result;
    }

    @RequestMapping(value = "declineRequest",method = RequestMethod.PUT)
    public ModelAndView declineRequest(HttpSession session, @RequestParam String from) throws SQLException {
        User to = (User) session.getAttribute("user");
        dao.declineFriendRequest(to.getEmail(),from);
        Connection connection = ConnectionProvider.getConnection();
        connection.commit();
        ModelAndView result = new ModelAndView("redirect:/services/FriendsPageController");
        return result;
    }
}
