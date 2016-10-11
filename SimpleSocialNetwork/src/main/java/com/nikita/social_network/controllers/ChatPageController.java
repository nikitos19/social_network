package com.nikita.social_network.controllers;

import com.nikita.social_network.dao.UserDAO;
import com.nikita.social_network.model.Message;
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
@RequestMapping(value = "ChatPageController")
public class ChatPageController {

    @Autowired
    private UserDAO dao;

    @RequestMapping(value = "chatWith",method = RequestMethod.GET)
    public ModelAndView chatWith(@RequestParam String email,@RequestParam String recipient, HttpSession session) throws SQLException {
        User currentUser = (User) session.getAttribute("user");
        List<Message> messages = dao.getAllMessage(currentUser.getEmail(),email);
        ModelAndView result = new ModelAndView("chat");
        result.addObject("messages",messages);
        return result;
    }

    @RequestMapping(value = "sendMessage",method = RequestMethod.POST)
    public ModelAndView sendMessage(@RequestParam String recipient,@RequestParam String message, HttpSession session){


        ModelAndView result = new ModelAndView("chat");
        return result;
    }
}
