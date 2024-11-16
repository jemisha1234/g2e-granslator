package com.example.g2e_translator.service;

import com.example.g2e_translator.model.Word;
import com.example.g2e_translator.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

// @Service annotation indicates this class handles quiz-related logic (e.g., randomizing quiz words).
@Service
public class QuizService {

    // @Autowired injects the WordRepository instance into the service class.
    @Autowired
    private WordRepository wordRepository;

    // This method gets a list of random words from the WordRepository for the quiz.
    // 'count' indicates the number of random words to fetch.
    public List<Word> getRandomWords(int count) {
        // Retrieve all words from the repository.
        List<Word> words = wordRepository.findAll();

        // If there are no words available, return an empty list.
        if (words.isEmpty()) {
            return Collections.emptyList(); // Return an empty list if no words are available.
        }

        // Shuffle the list of words to randomize the order.
        Collections.shuffle(words);

        // Return a sublist with the requested number of words.
        return words.subList(0, Math.min(count, words.size())); // Return 'count' words or the total number if less than
                                                                // 'count'.
    }

    // This method checks if the provided English translation (englishAnswer)
    // matches the stored Gujarati word.
    public boolean checkAnswer(String gujaratiWord, String englishAnswer) {
        // Find the word by the Gujarati translation.
        return wordRepository.findByGujarati(gujaratiWord)
                // Compare the user's answer (English) with the correct answer stored in the
                // database (ignoring case sensitivity).
                .map(word -> word.getEnglish().equalsIgnoreCase(englishAnswer))
                .orElse(false); // Return false if the word is not found.
    }
}
