package com.spring.assignment.controller;

import com.spring.assignment.domain.Anime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/maps")
public class MapsController {

    @RequestMapping(value = "/show", method = RequestMethod.GET)

    public String showMaps(Model model, HttpSession session){

        // session checking before allowing access

        if(session.getAttribute("login")==null){
            return "redirect:/user/login";
        }

        return "maps/location"; // display current location in maps
    }
}
