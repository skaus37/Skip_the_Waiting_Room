package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceBadTest {

    @Test
    public void getID() {
        // Arrange
        Service service = new Service("Checkup", "doctor");

        // Act
        String result = service.getID();

        // Assert (expected, actual, accurate within certain delta)
        assertEquals(null, result);
    }

    @Test
    public void getServiceName() {
        // Arrange
        Service service = new Service(null, "doctor");

        // Act
        String result = service.getServiceName();

        // Assert (expected, actual, accurate within certain delta)
        assertEquals(null, result);
    }

    @Test
    public void getRole() {
        // Arrange
        Service service = new Service("Checkup", null);

        // Act
        String result = service.getRole();

        // Assert (expected, actual, accurate within certain delta)
        assertEquals(null, result);
    }
}