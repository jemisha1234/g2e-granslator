package com.example.g2e_translator.controller;

import com.example.g2e_translator.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// This controller handles input of new words (English and Gujarati) by the user.
@Controller
public class InputWordsController {

    // Inject the WordService that handles the logic for saving words.
    @Autowired
    private WordService wordService;

    // This method handles GET requests to "/input-words" and displays the word
    // input page.
    @GetMapping("/input-words")
    public String showInputWordsPage(Model model) {
        return "input-words"; // Display the form where users can enter words.
    }

    // This method handles POST requests to save the entered words into the
    // database.
    @PostMapping("/input-words")
    public String saveWord(
            @RequestParam("english") String english, // The English word entered by the user.
            @RequestParam("gujarati") String gujarati, // The Gujarati word entered by the user.
            Model model) { // Model is used to display a success message after saving the word.

        wordService.saveWord(english, gujarati); // Call the service to save the word.
        model.addAttribute("message", "Word saved successfully!"); // Add a success message to the model.
        return "input-words"; // Return to the same input form page after saving.
    }
}
