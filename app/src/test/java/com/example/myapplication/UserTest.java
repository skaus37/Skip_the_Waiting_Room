package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void getFirstName() {
        // Arrange
        User user = new User("First Name", "Last Name", "employee", "email@gmail.com");

        // Act
        String result = user.getFirstName();

        // Assert (expected, actual, accurate within certain delta)
        assertEquals("First Name", result);
    }

    @Test
    public void setFirstName() {
        // Arrange
        User user = new User();

        // Act
        user.setFirstName("First Name");

        // Assert (expected, actual, accurate within certain delta)
        assertEquals("First Name", user.getFirstName());
    }

    @Test
    public void getLastName() {
        // Arrange
        User user = new User("First Name", "Last Name", "employee", "email@gmail.com");

        // Act
        String result = user.getLastName();

        // Assert (expected, actual, accurate within certain delta)
        assertEquals("Last Name", result);
    }

    @Test
    public void setLastName() {
        // Arrange
        User user = new User();

        // Act
        user.setLastName("Last Name");

        // Assert (expected, actual, accurate within certain delta)
        assertEquals("Last Name", user.getLastName());
    }

    @Test
    public void getAccountType() {
        // Arrange
        User user = new User("First Name", "Last Name", "employee", "email@gmail.com");

        // Act
        String result = user.getAccountType();

        // Assert (expected, actual, accurate within certain delta)
        assertEquals("employee", result);
    }

    @Test
    public void getEmail() {
        // Arrange
        User user = new User("First Name", "Last Name", "employee", "email@gmail.com");

        // Act
        String result = user.getEmail();

        // Assert (expected, actual, accurate within certain delta)
        assertEquals("email@gmail.com", result);
    }

    @Test
    public void setEmail() {
        // Arrange
        User user = new User();

        // Act
        user.setEmail("unittest@gmail.com");

        // Assert (expected, actual, accurate within certain delta)
        assertEquals("unittest@gmail.com", user.getEmail());
    }
}