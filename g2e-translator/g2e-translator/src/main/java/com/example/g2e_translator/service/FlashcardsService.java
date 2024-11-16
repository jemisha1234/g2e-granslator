package com.example.g2e_translator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.g2e_translator.model.Word;
import com.example.g2e_translator.repository.WordRepository;

// @Service annotation marks this class as a service, meaning it contains the business logic for flashcards.
@Service
public class FlashcardsService {

    // @Autowired automatically injects an instance of WordRepository into this
    // service class.
    @Autowired
    private WordRepository wordRepository;

    // This method fetches all the words from the WordRepository.
    // It retrieves the words from the database to be used as flashcards.
    public List<Word> getAllWords() {
        return wordRepository.findAll(); // Get all words from the database.
    }

    // This method gets a specific flashcard based on its index in the list.
    // 'words' is the list of words and 'index' is the current position of the word
    // in the list.
    public Word getFlashcardByIndex(List<Word> words, int index) {
        // Check if the index is valid, return the word if valid, otherwise return null.
        if (index >= 0 && index < words.size()) {
            return words.get(index); // Return the word at the specified index.
        }
        return null; // Return null if the index is out of bounds.
    }
}
