package com.spring.assignment.controller;

import com.spring.assignment.domain.User;
import com.spring.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET) // define method type
    //@ResponseBody // use response body just ot display some text other wise it will look for a template view
    public String index(Model model, HttpSession session){

        if(session.getAttribute("login")==null){
            return "redirect:/user/login";
        }
        List<User> users = userService.findAll(); // display user

        model.addAttribute("users", users); // use object as parameter
        return "home";
    }

}
