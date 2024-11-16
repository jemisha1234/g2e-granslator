package com.example.g2e_translator.service;

import com.example.g2e_translator.model.Word;
import com.example.g2e_translator.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// @Service marks this class as the service responsible for translation logic (Gujarati to English).
@Service
public class TranslationService {

    // @Autowired injects the WordRepository for accessing the database.
    @Autowired
    private WordRepository wordRepository;

    // This method translates a given Gujarati word into English.
    public String translateGujaratiToEnglish(String gujaratiWord) {
        // Find the word in the database by its Gujarati translation.
        Optional<Word> word = wordRepository.findByGujarati(gujaratiWord);

        // If the word is found, return its English translation; otherwise, return a
        // "not found" message.
        return word.map(Word::getEnglish).orElse("Translation not found");
    }
}
