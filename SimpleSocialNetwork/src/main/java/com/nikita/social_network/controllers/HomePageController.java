package com.nikita.social_network.controllers;

import com.nikita.social_network.ConnectionProvider;
import com.nikita.social_network.dao.UserDAO;
import com.nikita.social_network.model.User;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;


@Controller
@RequestMapping(value = "HomePageController")
public class HomePageController {
    @Autowired
    UserDAO dao;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView homePage(HttpSession session) throws SQLException {
        ModelAndView result = new ModelAndView("home");
        return result;
    }


    @RequestMapping(value = "/setAvatar", method = RequestMethod.POST)
    public ModelAndView setAvatar(@RequestParam MultipartFile photo, HttpSession session) throws IOException, SQLException {
        User currentUser = (User) session.getAttribute("user");

        if(photo.getSize()!=0){
            dao.setAvatar(photo.getBytes(), currentUser.getEmail());
            Connection c = ConnectionProvider.getConnection();
            c.commit();
            ModelAndView model = new ModelAndView("redirect:/services/HomePageController");
            return model;
        }
        else{
            ModelAndView model = new ModelAndView("home");
            model.addObject("error","select photo");
            return model;
        }
    }

    @RequestMapping(value = "/getPhoto")
    public ResponseEntity<byte[]> getPhoto(HttpSession session) throws SQLException, IOException {
        User currentUser = (User) session.getAttribute("user");
        User user = dao.getUser(currentUser.getEmail());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        if (user.getPhoto() == null) {
            String pathToImageSortBy = "no-avatar.gif";
            ClassLoader classLoader = HomePageController.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(pathToImageSortBy);
            byte[] bytes = IOUtils.toByteArray(inputStream);
            return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<byte[]>(user.getPhoto(), headers, HttpStatus.OK);
        }
    }
}
