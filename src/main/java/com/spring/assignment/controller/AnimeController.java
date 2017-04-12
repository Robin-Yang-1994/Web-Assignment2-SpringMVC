package com.spring.assignment.controller;

import com.spring.assignment.domain.Anime;
import com.spring.assignment.domain.AnimeSearch;
import com.spring.assignment.domain.User;
import com.spring.assignment.service.AnimeService;
import com.spring.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/anime")
public class AnimeController {

    @Autowired
    AnimeService animeService;

    @RequestMapping(value = "/show", method = RequestMethod.GET) // define method type
    public String showAnime(Model model){

        List<Anime> anime = animeService.findAll(); // display user
        model.addAttribute("anime", anime); // use object as parameter
        return "home";
    }

    @RequestMapping(value = "/addAnime", method = RequestMethod.GET) // get user and show user
    public String AddAnimeView(Model model){

        Anime anime = new Anime();
        model.addAttribute("Anime", anime); // uses model
        return "addAnimeForm";
    }

    @RequestMapping(value = "/addAnime", method = RequestMethod.POST) // data passed from form using post
    public String addAnime(Model model, @Valid @ModelAttribute("Anime")Anime anime, BindingResult bindingResult){

        if(bindingResult.hasErrors())
        {
            model.addAttribute("Anime", anime);
            model.addAttribute("message", "Please fill in each field"); // sends error message as parameter
            return "addAnimeForm";
        }
        animeService.save(anime); // saves into database
        return "redirect:/anime/show";
        //return "Registration complete and user has been added to data base. New user is " + user.getFname()+ " " +user.getLname();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET) // define method type
    public String sAnime(Model model){

        AnimeSearch anime = new AnimeSearch(); // display user
        model.addAttribute("anime", anime); // use object as parameter
        return "home";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST) // define method type
    public String searchAnimeForm(Model model, @ModelAttribute("search")AnimeSearch aSearch){

        List<Anime> result = animeService.searchAnime(aSearch);
        model.addAttribute("aSearch", aSearch);
        model.addAttribute("result", result);
        return "redirect:/";
    }

}
