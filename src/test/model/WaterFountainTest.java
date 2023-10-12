package model;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WaterFountainTest {

    WaterFountain waterFountain;

    FavouritePins favPins;

    @BeforeEach
    void runBefore() {
        waterFountain = new WaterFountain("LIFE");

    }

    @Test
    void testConstructor() {
        assertEquals("LIFE", waterFountain.getLocation());
        assertEquals("Water Fountain", waterFountain.getTag());
        assertEquals("Working", waterFountain.getStatus());
    }

    @Test
    void testIsBrokenFalse() {
        assertFalse(waterFountain.isBroken());
    }

    @Test
    void testIsBrokenTrue() {
        waterFountain.setStatus("Broken");

        assertTrue(waterFountain.isBroken());
    }

    @Test
    void testSetDirection() {
        waterFountain.setDirection("Second floor by the men's bathrooms");
        assertEquals("Second floor by the men's bathrooms", waterFountain.getDirections());
    }


}