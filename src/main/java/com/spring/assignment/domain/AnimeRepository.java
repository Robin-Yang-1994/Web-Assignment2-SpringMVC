package com.spring.assignment.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime, Long> {  // extends spring java database

    @Query("SELECT anime FROM Anime anime Where lower(anime.animeName) Like %?1%")  // querying with eloquent
                                                                                    // lower change all database results
    List<Anime> searchAnime(String animeName); // add to list
}

