package model;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WaterFountainTest {

    WaterFountain waterFountain;


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
    void testSetStatus() {

    }

    @Test
    void testAddFavourite() {

    }
}
