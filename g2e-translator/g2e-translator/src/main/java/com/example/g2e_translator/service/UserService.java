package com.example.g2e_translator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.g2e_translator.model.User;
import com.example.g2e_translator.repository.UserRepository;

import java.util.Optional;

// @Service annotation marks this class as the service that handles user-related logic.
@SuppressWarnings("unused")
@Service // Business logic of the code related to users.
public class UserService {

    // Declare the UserRepository as a final variable to inject via the constructor.
    private final UserRepository userRepository;

    // @Autowired marks this constructor for dependency injection of UserRepository.
    // Constructor Injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // This method handles user registration.
    // It checks if the username is already taken, and if not, it saves the new
    // user.
    public String registerUser(String username, String password) {
        // Check if a user with the given username already exists.
        Optional<User> existingUser = userRepository.findByUsername(username);

        // If the user exists, return a message indicating the username is taken.
        if (existingUser.isPresent()) {
            return "User already exists!";
        }

        // Create a new User object and set its username and password.
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        // Save the new user to the database.
        userRepository.save(user);
        return "User registered successfully!";
    }

    // This method validates a user's login by checking their username and password.
    public User validateUser(String username, String password) {
        // Find the user by their username and password.
        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);

        // Return the user if found, otherwise return null.
        return user.orElse(null);
    }
}
