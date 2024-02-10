package com.revature.todoapp.service;

import com.revature.todoapp.entity.User;
import com.revature.todoapp.exception.AccessDeniedException;
import com.revature.todoapp.exception.UsernameAlreadyExistsException;
import com.revature.todoapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //Create an account to hold to-dos
    public User register(User newUser) {
        if (userRepository.findByUsername(newUser.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        return userRepository.save(newUser);
    }


    //Login into to account
    public User login(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())
                .orElseThrow(() -> new AccessDeniedException("Invalid username or password"));
    }
}
