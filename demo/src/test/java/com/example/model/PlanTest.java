package com.example.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class PlanTest {

    private Plan basicPlan;
    private Plan premiumPlan;

    @BeforeEach
    void setUp() {
        basicPlan = new Plan("Basic", 9.99, "Basic access");
        premiumPlan = new Plan("Premium", 19.99, "Full access");
    }

    @Test
    void testConstructor() {
        assertEquals("Basic", basicPlan.getName());
        assertEquals(9.99, basicPlan.getPrice(), 0.01);
        assertEquals("Basic access", basicPlan.getDescription());
    }

    @Test
    void testGetters() {
        assertEquals("Premium", premiumPlan.getName());
        assertEquals(19.99, premiumPlan.getPrice(), 0.01);
        assertEquals("Full access", premiumPlan.getDescription());
    }

    @Test
    void testToString() {
        String expected = "Basic - $9,99 - Basic access";
        assertEquals(expected, basicPlan.toString());
    }

    @Test
    void testPositivePrice() {
        assertTrue(basicPlan.getPrice() > 0);
        assertTrue(premiumPlan.getPrice() > 0);
    }

    @Test
    void testNameNotEmpty() {
        assertNotNull(basicPlan.getName());
        assertFalse(basicPlan.getName().isEmpty());
    }
}