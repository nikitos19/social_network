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
    //TestServlet/m1
//    public void m1(){}
//    //TestServlet/m2
//    public void m2(){}
//
//    public void doPost(HttpServletRequest req, HttpServletResponse res){
//        req.getRequestURL();// localhost:8080//TestServlet/m1
//
//        req.setAttribute("user", new Object());
//    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView entrySubmit(@RequestParam String email, @RequestParam String password, HttpServletRequest req) throws SQLException {
        //        req.getParameter("email");

        User u = dao.getUser(email, password);
        //если u=null,то пользователя с указанным email and passwjrd not exists,его нужно напрваить на эту же страничку с инфо об ерор.если u!=null то его надо положить в сессию и отправить redirect =homepagecontroller
        System.out.println(email + " " + password);

        if (u==null) {
            ModelAndView result = new ModelAndView("entry");
            result.addObject("error", "User not found");
            return result;
        }
        ModelAndView result = new ModelAndView("redirect:/services/HomePageController");
        req.getSession().setAttribute("user", u);
        return result;
    }

}
