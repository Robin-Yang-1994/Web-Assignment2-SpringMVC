package com.spring.assignment.controller;

import com.spring.assignment.domain.Anime;
import com.spring.assignment.domain.User;
import com.spring.assignment.service.AnimeService;
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
    AnimeService animeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, HttpSession session){

        if(session.getAttribute("login")==null){
            return "redirect:/user/login";
        }
        List<Anime> anime = animeService.findAll(); // display user

        model.addAttribute("anime", anime); // use object as parameter
        return "home";
    }

}

