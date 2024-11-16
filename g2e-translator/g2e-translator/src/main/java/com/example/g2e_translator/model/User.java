package com.example.g2e_translator.model;

import jakarta.persistence.*;

// @Entity indicates that this class is a JPA entity, meaning it will be mapped to a table in the database.
@Entity
public class User {

    // @Id marks this field as the primary key of the entity.
    // @GeneratedValue specifies how the primary key is generated.
    // GenerationType.IDENTITY means the database will automatically generate the
    // primary key when a new record is inserted.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // This field will store the username of the user in the database.
    private String username;

    // This field will store the password of the user in the database.
    private String password;

    // Getter for 'id' field.
    // This method returns the value of the 'id' field.
    public Long getId() {
        return id;
    }

    // Setter for 'id' field.
    // This method sets the value of the 'id' field.
    public void setId(Long id) {
        this.id = id;
    }

    // Getter for 'username' field.
    // This method returns the value of the 'username' field.
    public String getUsername() {
        return username;
    }

    // Setter for 'username' field.
    // This method sets the value of the 'username' field.
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for 'password' field.
    // This method returns the value of the 'password' field.
    public String getPassword() {
        return password;
    }

    // Setter for 'password' field.
    // This method sets the value of the 'password' field.
    public void setPassword(String password) {
        this.password = password;
    }

    // Getters and Setters provide a way to retrieve and update the fields of this
    // class.
}
