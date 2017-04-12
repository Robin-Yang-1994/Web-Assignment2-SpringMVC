package com.spring.assignment.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime, Long> {

    @Query("SELECT a FROM Anime a Where a.animeName Like ?1")
    List<Anime> searchAnime(String animeName);
}

