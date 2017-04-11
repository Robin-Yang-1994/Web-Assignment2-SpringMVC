package com.spring.assignment.controller;

import com.spring.assignment.domain.User;
import com.spring.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerView(Model model){

        User user = new User();
        model.addAttribute("User", user);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model, @ModelAttribute("User")User user){

        userService.save(user);
        return "redirect:/";
        //return "Registration complete and user has been added to data base. New user is " + user.getFname()+ " " +user.getLname();
    }

    @RequestMapping(value = "/delete/{user}", method = RequestMethod.GET)// define url to delete using post data from view
    public String deleteUser(@PathVariable User user){

        String name = user.getFname()+" "+user.getLname();
        userService.delete(user); // delete operation

        return "redirect:/";

    }
}
