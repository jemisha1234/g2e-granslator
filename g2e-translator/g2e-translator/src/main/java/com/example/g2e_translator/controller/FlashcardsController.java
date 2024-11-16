package com.example.g2e_translator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.g2e_translator.model.Word;
import com.example.g2e_translator.service.FlashcardsService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

// This is a Spring MVC controller class that handles the logic for the Flashcards feature.
@Controller
public class FlashcardsController {

    // @Autowired automatically injects the FlashcardsService when this controller
    // is created.
    @Autowired
    private FlashcardsService flashcardsService;

    // This method handles GET requests to "/flashcards" and displays flashcards to
    // the user.
    @GetMapping("/flashcards")
    public String showFlashcards(
            @RequestParam(value = "index", defaultValue = "0") int index, // The current index of the flashcard.
            Model model, // Model is used to pass data from the controller to the view (HTML page).
            HttpSession session) { // HttpSession is used to store data across multiple requests (like storing
                                   // flashcards).

        // Get the flashcards from the session if they exist, otherwise fetch from the
        // database.
        @SuppressWarnings("unchecked")
        List<Word> words = (List<Word>) session.getAttribute("flashcards");

        if (words == null) {
            words = flashcardsService.getAllWords(); // Fetch all words for flashcards if not already in session.
            session.setAttribute("flashcards", words); // Store flashcards in the session for future use.
        }

        // Get the current flashcard using the index.
        Word currentWord = flashcardsService.getFlashcardByIndex(words, index);

        // Add the current flashcard and some additional info (index, total words) to
        // the model to display in the view.
        model.addAttribute("word", currentWord);
        model.addAttribute("currentIndex", index);
        model.addAttribute("total", words.size());

        return "flashcards"; // Return the name of the Thymeleaf template that displays the flashcard.
    }
}
