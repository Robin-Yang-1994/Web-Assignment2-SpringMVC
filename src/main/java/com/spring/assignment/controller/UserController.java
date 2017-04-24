package com.spring.assignment.controller;

import com.spring.assignment.domain.LoginAuthentication;
import com.spring.assignment.domain.User;
import com.spring.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET) // get user and show user
    public String registerView(Model model){

        User user = new User();
        model.addAttribute("User", user); // uses model
        return "user/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST) // data passed from form using post
    public String register(Model model, @Valid @ModelAttribute("User")User user, BindingResult bindingResult){

        if(bindingResult.hasErrors())
        {
            model.addAttribute("user", user);
            model.addAttribute("type", "warning");
            model.addAttribute("message", "Please fill in all fields"); // sends error message as parameter
            return "user/register";
        }
        userService.save(user); // saves into database
        model.addAttribute("type", "success");
        model.addAttribute("message", "Congratulations, You have successfully created your account at Love Anime. Please signin.");
        return "user/login";
        //return "Registration complete and user has been added to data base. New user is " + user.getFname()+ " " +user.getLname();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET) // get user and show user
    public String loginView(Model model){

        LoginAuthentication user = new LoginAuthentication();
        model.addAttribute("User", user); // uses model
        return "user/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST) // data passed from form using post
    public String loginProcess(Model model, @Valid @ModelAttribute("User")LoginAuthentication user, BindingResult bindingResult,
                               HttpSession session){

        if(bindingResult.hasErrors()) // error check for empty fields
        {
            model.addAttribute("user", user);
            model.addAttribute("type", "warning");
            model.addAttribute("message", "Please fill in all fields"); // sends error message as parameter
            return "user/login";
        }

        if(userService.login(user)==null || userService.login(user).size()==0) // check if there is any matching data
        {
            model.addAttribute("user", user);
            model.addAttribute("type", "danger");
            model.addAttribute("message", "Your account information are does not match with the system"); // login error message
            return "user/login";
        }

        session.setAttribute("login", true);
        //String name = user.getFname();
//        model.addAttribute("Auth", "Username is " + user.getFname()); // uses model
        return "redirect:/";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST) // log out user 
    public String logout(Model model, HttpSession session){

        session.removeAttribute("login");
        return "redirect:/user/login";
    }

    @RequestMapping(value = "/update/{user}", method = RequestMethod.GET)
    public String updateView(Model model, @PathVariable User user){ // display view for update form

        model.addAttribute("User", user);
        return "/user/updateUser";
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
