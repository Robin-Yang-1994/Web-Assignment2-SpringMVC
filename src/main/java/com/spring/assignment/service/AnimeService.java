package com.spring.assignment.service;

import com.spring.assignment.domain.*;
import com.spring.assignment.domain.Anime;
import com.spring.assignment.domain.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService {
    @Autowired
    AnimeRepository animeRepository;  // using anime repository for querying

    public Anime save(Anime a){ // save method

        return animeRepository.save(a); // saving a results to database
    }

    public List<Anime> findAll(){ // get all anime

        return animeRepository.findAll();
    }

    public List<Anime> searchAnime(AnimeSearch anime){

        return animeRepository.searchAnime(anime.getAnimeName()); // get anime name after search
    }

    public void delete(Anime anime){ // define delete method in model

        animeRepository.delete(anime); // delete the anime data passed from controller
    }


}
