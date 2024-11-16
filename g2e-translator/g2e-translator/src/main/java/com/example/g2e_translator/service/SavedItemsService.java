package com.example.g2e_translator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.g2e_translator.model.Word;
import com.example.g2e_translator.repository.WordRepository;

import java.util.List;

// @Service annotation marks this class as a service that handles retrieving saved items (words).
@Service
public class SavedItemsService {

    // @Autowired automatically injects the WordRepository instance into this
    // service.
    @Autowired
    private WordRepository wordRepository;

    // This method fetches all words stored in the database.
    public List<Word> getAllWords() {
        return wordRepository.findAll(); // Return all the saved words.
    }
}
