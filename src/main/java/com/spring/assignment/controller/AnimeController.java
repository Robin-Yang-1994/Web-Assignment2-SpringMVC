package com.spring.assignment.controller;

import com.spring.assignment.domain.Anime;
import com.spring.assignment.domain.AnimeSearch;
import com.spring.assignment.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller // define controller
@RequestMapping(value = "/anime")  // mapping url
public class AnimeController {

    @Autowired
    AnimeService animeService;  // using anime service via auto wire

    @RequestMapping(value = "/addAnime", method = RequestMethod.GET) // get add anime form

    public String AddAnimeView(Model model, HttpSession session){

        if(session.getAttribute("login")==null){  // session checking
            return "redirect:/user/login";
        }

        Anime anime = new Anime();  // new anime object
        model.addAttribute("Anime", anime); // pass anime to form
        return "addAnimeForm";
    }

    @RequestMapping(value = "/addAnime", method = RequestMethod.POST) // data passed to method via form method
    public String addAnime(Model model, @Valid @ModelAttribute("Anime")Anime anime, BindingResult bindingResult){
                                        // using validation and anime model
        if(bindingResult.hasErrors()) // error finding with binding results
        {
            model.addAttribute("Anime", anime); // define and pass error message to view
            model.addAttribute("message", "Please fill in each field");
            return "addAnimeForm";
        }
        animeService.save(anime); // else no error and saves into database
        return "redirect:/"; // return home view
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET) // show search form method
    public String searchAnimeForm(Model model, HttpSession session){

        if(session.getAttribute("login")==null){  // session checking
            return "redirect:/user/login";
        }

        AnimeSearch searchForm = new AnimeSearch();
        model.addAttribute("search", searchForm);
        return "/anime/searchAnimeForm";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST) // search keywords are posted via forms
    public String searchAnime(Model model, @ModelAttribute("search")AnimeSearch searchForm){

        List<Anime> anime = animeService.searchAnime(searchForm); // list to hold all values searched in anime service method
        long count = anime.size(); // counts returned results using list size to show amount of results founded
        model.addAttribute("anime", anime);
        model.addAttribute("count", count); // pass data to view
        return "/anime/searchAnimeForm";
    }

    @RequestMapping(value = "/update/{anime}", method = RequestMethod.GET)
    public String updateView(Model model, @PathVariable Anime anime, HttpSession session){ // display view for update form

        if(session.getAttribute("login")==null){  // session checking
            return "redirect:/user/login";
        }

        model.addAttribute("Anime", anime);
        return "/anime/updateAnime";
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST) // get data from form via post
    public String updateAnime(Model model, @ModelAttribute Anime anime){ // update user and redirect to view

        animeService.save(anime); // over write data in database
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{anime}", method = RequestMethod.GET)// define url to delete using get data from view
    public String deleteAnime(@PathVariable Anime anime){

        animeService.delete(anime); // get the anime from database according to the get data
        return "redirect:/";
    }

}
