package com.spring.assignment.controller;

import com.spring.assignment.Anime.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerView(Model model){

        User user = new User();
        model.addAttribute("User", user);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(Model model, @ModelAttribute("User")User user){

        return "Register complete" + " " + user.getFname()+ " " +user.getLname();
    }
}
