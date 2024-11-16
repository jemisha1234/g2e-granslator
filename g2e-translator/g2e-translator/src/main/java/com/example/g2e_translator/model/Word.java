package com.example.g2e_translator.model;

import jakarta.persistence.*;

// @Entity indicates that this class represents a table in the database, called 'Word'.
@Entity
public class Word {

    // @Id marks this field as the primary key of the entity (used to uniquely
    // identify each word).
    // @GeneratedValue specifies how the primary key is generated, where
    // GenerationType.IDENTITY allows the database to auto-generate this value.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // This field stores the English version of the word.
    private String english;

    // This field stores the Gujarati version of the word.
    private String gujarati;

    // Getter for 'id' field.
    // Returns the unique identifier (ID) of the word.
    public Long getId() {
        return id;
    }

    // Setter for 'id' field.
    // Sets the unique identifier (ID) for the word.
    public void setId(Long id) {
        this.id = id;
    }

    // Getter for 'english' field.
    // Returns the English translation of the word.
    public String getEnglish() {
        return english;
    }

    // Setter for 'english' field.
    // Sets the English translation for the word.
    public void setEnglish(String english) {
        this.english = english;
    }

    // Getter for 'gujarati' field.
    // Returns the Gujarati translation of the word.
    public String getGujarati() {
        return gujarati;
    }

    // Setter for 'gujarati' field.
    // Sets the Gujarati translation for the word.
    public void setGujarati(String gujarati) {
        this.gujarati = gujarati;
    }

    // Getters and Setters provide a way to retrieve and modify the fields of this
    // class.
}
