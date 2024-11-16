package com.example.g2e_translator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.g2e_translator.model.Word;
import com.example.g2e_translator.repository.WordRepository;

// This is a REST controller that handles saving new words (both English and Gujarati).
// It uses @RestController because it directly returns data (as a string) instead of rendering a view (HTML page).
@RestController
@RequestMapping("/save-word") // Map all requests starting with "/save-word" to this controller.
public class SaveWordController {

    // Inject the WordRepository to interact with the database.
    @Autowired
    private WordRepository wordRepository;

    // This method handles POST requests to "/save-word" and saves a new word
    // (English and Gujarati).
    @PostMapping
    public String saveWord(
            @RequestParam String english, // The English word submitted by the user.
            @RequestParam String gujarati) { // The Gujarati word submitted by the user.

        // Create a new Word object and set its English and Gujarati properties.
        Word word = new Word();
        word.setEnglish(english);
        word.setGujarati(gujarati);

        // Save the word to the database.
        wordRepository.save(word);

        // Return a success message after saving the word.
        return "Word saved successfully!";
    }
}
