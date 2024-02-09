package com.revature.todoapp.service;

import com.revature.todoapp.entity.User;
import com.revature.todoapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //Create an account to hold to-dos
    public User addUser(User newUser) {
        if (newUser.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username is blank");
        }
        if (newUser.getPassword().length() < 4) {
            throw new IllegalArgumentException("Password must be at least 4 characters long");
        }
        if (userRepository.findByUsername(newUser.getUsername()).isPresent()) {
            //todo: create custom exception
            //throw new UsernameAlreadyExistsException("Username already exists");
        }
        return userRepository.save(newUser);
    }


    //Login into to account
    public User login(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()).orElseThrow();
                //todo: create custom exception
                //.orElseThrow()) -> new InvalidUsernameOrPasswordException ("Invalid username or password");
    }
}
