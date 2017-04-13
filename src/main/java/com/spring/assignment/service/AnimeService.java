package com.spring.assignment.service;

import com.spring.assignment.domain.*;
import com.spring.assignment.domain.Anime;
import com.spring.assignment.domain.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by web on 10/04/17.
 */
@Service
public class AnimeService {
    @Autowired
    AnimeRepository animeRepository;

    public Anime save(Anime a){ // save method

        return animeRepository.save(a);
    }

    public List<Anime> findAll(){ // get all user
        //return userRepository.findByFnameAndEmailAndPassword("test","test","test");
        return animeRepository.findAll();
    }

    public List<Anime> searchAnime(AnimeSearch anime){

        if(anime.getAnimeName().isEmpty()){

            return animeRepository.findAll();
        }
        return animeRepository.searchAnime(anime.getAnimeName());
    }

    public void delete(Anime anime){ // define delete method in model

        animeRepository.delete(anime);
    }


}
