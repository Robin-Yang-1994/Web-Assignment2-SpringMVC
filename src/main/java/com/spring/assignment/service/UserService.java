package com.spring.assignment.service;

import com.spring.assignment.Anime.User;
import com.spring.assignment.Anime.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by web on 10/04/17.
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User save(User u){

        return userRepository.save(u);
    }
}
