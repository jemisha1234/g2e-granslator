package com.example.g2e_translator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.g2e_translator.service.UserService;

// This controller manages the user registration process.
@Controller
public class RegisterController {

    // Inject the UserService to handle the logic for registering new users.
    @Autowired
    private UserService userService;

    // This method handles GET requests to "/register" and displays the registration
    // form.
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // Display the registration page (register.html).
    }

    // This method handles POST requests to register a new user when the form is
    // submitted.
    @PostMapping("/register")
    public String registerUser(
            @RequestParam("username") String username, // The username entered by the user.
            @RequestParam("password") String password, // The password entered by the user.
            Model model) { // Model is used to pass data (like success/failure messages) to the view.

        // Call the service to register the user and return a result message.
        String result = userService.registerUser(username, password);

        // Add the result message (success or failure) to the model to display in the
        // view.
        model.addAttribute("message", result);

        return "register"; // Stay on the registration page and show the result message.
    }
}
