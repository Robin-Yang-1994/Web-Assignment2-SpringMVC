package com.spring.assignment.service;

import com.spring.assignment.domain.LoginAuthentication;
import com.spring.assignment.domain.User;
import com.spring.assignment.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository; // querying through user repository

    public User save(User u){ // save method

        return userRepository.save(u);
    }

    public List<User> findAll(){ // get all user

        return userRepository.findAll();
    }

    public void delete(User user){ // define delete method in model

        userRepository.delete(user);
    }

    public List<User> login(LoginAuthentication user){  // login method

        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()); // method check in user repository
    }

}
