package com.example.g2e_translator.controller;

import com.example.g2e_translator.model.Word;
import com.example.g2e_translator.service.QuizService;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// This controller manages the quiz functionality.
@Controller
public class QuizController {

    // Total number of questions in the quiz.
    private static final int TOTAL_QUESTIONS = 10;

    // Inject the QuizService to handle quiz logic.
    @Autowired
    private QuizService quizService;

    // This method starts the quiz by getting random words for the quiz.
    @GetMapping("/quiz")
    public String startQuiz(HttpSession session, Model model) {
        // Fetch 10 random words for the quiz.
        List<Word> quizWords = quizService.getRandomWords(TOTAL_QUESTIONS);

        // Store the quiz words, current question index, score, and wrong answers in the
        // session.
        session.setAttribute("quizWords", quizWords);
        session.setAttribute("currentQuestionIndex", 0);
        session.setAttribute("score", 0);
        session.setAttribute("wrongAnswers", new ArrayList<Word>());

        // If no words are available for the quiz, display a message.
        if (quizWords.isEmpty()) {
            model.addAttribute("message", "No words available for the quiz.");
            return "quiz"; // Return the quiz page with the message.
        }

        // Get the first word for the quiz.
        Word currentWord = quizWords.get(0);
        model.addAttribute("word", currentWord);
        model.addAttribute("currentQuestion", 1);
        model.addAttribute("totalQuestions", TOTAL_QUESTIONS);

        return "quiz"; // Show the quiz page with the first word.
    }

    // This method processes the answer submitted by the user during the quiz.
    @PostMapping("/quiz")
    public String submitAnswer(
            @RequestParam("gujarati") String gujaratiWord, // The Gujarati word of the current question.
            @RequestParam("answer") String englishAnswer, // The English translation provided by the user.
            HttpSession session, Model model) {

        // Get the current quiz state from the session (current index, score, quiz
        // words, and wrong answers).
        int currentQuestionIndex = (int) session.getAttribute("currentQuestionIndex");
        int score = (int) session.getAttribute("score");
        @SuppressWarnings("unchecked")
        List<Word> quizWords = (List<Word>) session.getAttribute("quizWords");
        @SuppressWarnings("unchecked")
        List<Word> wrongAnswers = (List<Word>) session.getAttribute("wrongAnswers");

        // Get the current word for this question.
        Word currentWord = quizWords.get(currentQuestionIndex);

        // Check if the user's answer is correct.
        if (quizService.checkAnswer(gujaratiWord, englishAnswer)) {
            score++; // Increase score if the answer is correct.
            session.setAttribute("score", score);
        } else {
            // Add the incorrect answer to the list of wrong answers.
            wrongAnswers.add(currentWord);
        }

        currentQuestionIndex++; // Move to the next question.
        session.setAttribute("currentQuestionIndex", currentQuestionIndex);

        // If there are more questions, load the next one. Otherwise, show the quiz
        // results.
        if (currentQuestionIndex < TOTAL_QUESTIONS) {
            Word nextWord = quizWords.get(currentQuestionIndex);
            model.addAttribute("word", nextWord);
            model.addAttribute("currentQuestion", currentQuestionIndex + 1);
            model.addAttribute("totalQuestions", TOTAL_QUESTIONS);
        } else {
            // Quiz is finished, display the final score and wrong answers.
            model.addAttribute("finalScore", score);
            model.addAttribute("totalQuestions", TOTAL_QUESTIONS);
            model.addAttribute("wrongAnswers", wrongAnswers);

            // Clear quiz data from the session.
            session.removeAttribute("quizWords");
            session.removeAttribute("currentQuestionIndex");
            session.removeAttribute("score");
        }

        return "quiz"; // Show the quiz page (either next question or results).
    }
}
