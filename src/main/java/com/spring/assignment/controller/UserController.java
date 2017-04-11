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

    @RequestMapping(value = "/register", method = RequestMethod.GET) // get user and show user
    public String registerView(Model model){

        User user = new User();
        model.addAttribute("User", user); // uses model
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST) // data passed from form using post
    public String register(Model model, @ModelAttribute("User")User user){

        userService.save(user); // saves into database
        return "redirect:/";
        //return "Registration complete and user has been added to data base. New user is " + user.getFname()+ " " +user.getLname();
    }

    @RequestMapping(value = "/update/{user}", method = RequestMethod.GET)
    public String updateView(Model model, @PathVariable User user){ // display view for update form

        model.addAttribute("User", user);
        return "updateUser";
    }

    @RequestMapping(value = "/update/", method = RequestMethod.POST) // get data from form via post
    public String updateUser(Model model, @ModelAttribute User user){ // update user and redirect to view

        userService.save(user); // over write data in database
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{user}", method = RequestMethod.GET)// define url to delete using post data from view
    public String deleteUser(@PathVariable User user){

//        String name = user.getFname()+" "+user.getLname();
        userService.delete(user); // delete operation
        return "redirect:/";
    }
}
