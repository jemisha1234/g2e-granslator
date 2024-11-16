package com.example.g2e_translator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.g2e_translator.model.Word;
import com.example.g2e_translator.service.SavedItemsService;

import java.util.List;

// This controller handles retrieving the saved items (words) from the database and displaying them.
@Controller
public class RetrieveItemsController {

    // Inject the SavedItemsService that handles the logic for retrieving words.
    @Autowired
    private SavedItemsService savedItemsService;

    // This method handles GET requests to "/saved-items" and displays the saved
    // words to the user.
    @GetMapping("/saved-items")
    public String showSavedItems(Model model) {
        // Get all words saved in the system.
        List<Word> words = savedItemsService.getAllWords();

        // Add the list of words to the model to display them in the view (HTML page).
        model.addAttribute("words", words);

        return "saved-items"; // Return the name of the Thymeleaf template that shows the saved words.
    }
}
