package com.example.g2e_translator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.g2e_translator.model.User;

import java.util.Optional;

// @Repository annotation indicates that this interface is a Spring Data repository, 
// which is responsible for interacting with the database (specifically for the 'User' entity).
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository<User, Long> means this repository handles CRUD operations for
    // the 'User' entity,
    // where 'User' is the entity class, and 'Long' is the type of the primary key.

    // This method is used to find a user by their username in the database.
    // The method name 'findByUsername' follows a naming convention understood by
    // Spring Data JPA.
    // Spring will automatically implement the query to find a user by the
    // 'username' field.
    Optional<User> findByUsername(String username);

    // This method is used to find a user by both their username and password in the
    // database.
    // This method is useful for validating login credentials.
    // Spring Data JPA will automatically generate the appropriate SQL query based
    // on the method name.
    Optional<User> findByUsernameAndPassword(String username, String password);
}
