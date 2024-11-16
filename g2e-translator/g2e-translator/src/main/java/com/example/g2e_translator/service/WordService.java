package com.example.g2e_translator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.g2e_translator.model.Word;
import com.example.g2e_translator.repository.WordRepository;

// @Service annotation marks this class as the service responsible for handling word-related logic (e.g., saving words).
@Service
public class WordService {

    // @Autowired injects the WordRepository into the service to allow interaction
    // with the database.
    @Autowired
    private WordRepository wordRepository;

    // This method handles saving a word (both English and Gujarati) into the
    // database.
    public void saveWord(String english, String gujarati) {
        // Create a new Word object and set its English and Gujarati translations.
        Word word = new Word();
        word.setEnglish(english);
        word.setGujarati(gujarati);

        // Save the word to the database.
        wordRepository.save(word);
    }
}
