package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceTest {

    @Test
    public void getID() {
        // Arrange
        Service service = new Service("1", "Checkup", "doctor");

        // Act
        String result = service.getID();

        // Assert (expected, actual, accurate within certain delta)
        assertEquals("1", result);
    }

    @Test
    public void getServiceName() {
        // Arrange
        Service service = new Service("1", "Checkup", "doctor");

        // Act
        String result = service.getServiceName();

        // Assert (expected, actual, accurate within certain delta)
        assertEquals("Checkup", result);
    }

    @Test
    public void getRole() {
        // Arrange
        Service service = new Service("1", "Checkup", "doctor");

        // Act
        String result = service.getRole();

        // Assert (expected, actual, accurate within certain delta)
        assertEquals("doctor", result);
    }
}