package com.spring.assignment.service;

import com.spring.assignment.domain.User;
import com.spring.assignment.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by web on 10/04/17.
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User save(User u){ // save method

        return userRepository.save(u);
    }

    public List<User> findAll(){ // get all user
            //return userRepository.findByFnameAndEmailAndPassword("test","test","test");
        return userRepository.findAll();
    }

    public void delete(User user){ // define delete method in model

        userRepository.delete(user);
    }

}
