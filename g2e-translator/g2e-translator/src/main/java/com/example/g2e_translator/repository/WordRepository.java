package com.example.g2e_translator.repository;

import com.example.g2e_translator.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// @Repository annotation marks this interface as a repository for interacting with the 'Word' entity in the database.
@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    // JpaRepository<Word, Long> means this repository manages CRUD operations for
    // the 'Word' entity,
    // where 'Word' is the entity class and 'Long' is the type of its primary key.

    // This method finds a word by its Gujarati translation in the database.
    // The method name 'findByGujarati' follows a naming convention understood by
    // Spring Data JPA.
    // Spring will automatically implement the SQL query to find a word by its
    // 'gujarati' field.
    Optional<Word> findByGujarati(String gujarati);
}
