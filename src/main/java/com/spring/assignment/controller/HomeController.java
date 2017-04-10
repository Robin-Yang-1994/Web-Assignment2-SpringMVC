package com.spring.assignment.controller;

import com.spring.assignment.Anime.Animes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET) // define method type
    //@ResponseBody // use response body just ot display some text other wise it will look for a template view
    public String index(Model model){

        Animes animes = new Animes(); // new object

        animes.setAnimeName("Kill la Kill");
        animes.setGenre("Comedy");
        animes.setDescription("Girl trying to fight for dad");

        model.addAttribute("Anime", animes); // use object as parameter
        return "home";

    }

}
