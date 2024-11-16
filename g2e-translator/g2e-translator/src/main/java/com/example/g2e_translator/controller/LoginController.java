package com.example.g2e_translator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.g2e_translator.model.User;
import com.example.g2e_translator.service.UserService;

import jakarta.servlet.http.HttpSession;



// This controller manages the login process for users.
@Controller
public class LoginController {

    // Inject the UserService to handle user authentication.
    @Autowired
    private UserService userService;

    // This method handles GET requests to "/login" and displays the login form.
    @GetMapping("/login")
    public String showLoginForm() {
        return "index"; // Show the login page (index.html).
    }

    // This method handles POST requests when the user submits the login form.
    @PostMapping("/login")
    public String loginUser(
            @RequestParam("username") String username, // The username entered by the user.
            @RequestParam("password") String password, // The password entered by the user.
            HttpSession session, // HttpSession stores the logged-in user info.
            Model model) { // Model is used to display error messages if login fails.

        // Check if the user credentials are correct.
        User user = userService.validateUser(username, password);

        if (user != null) {
            // If login is successful, store the user object in the session.
            session.setAttribute("user", user);
            return "redirect:/main-menu"; // Redirect the user to the main menu.
        } else {
            // If login fails, show an error message on the login page.
            model.addAttribute("error", "Invalid username or password");
            return "index"; // Stay on the login page and show the error message.
        }
    }

    // This method shows the main menu after the user logs in successfully.
    @GetMapping("/main-menu")
    public String showMainMenu(Model model, HttpSession session) {
        // Check if a user is stored in the session (if logged in).
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // If no user is in session, redirect to the login page.
        }
        // Add the username to the model to display it on the main menu page.
        model.addAttribute("username", user.getUsername());
        return "main-menu"; // Display the main menu page.
    }
}
