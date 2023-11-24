package model;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// test class for WaterFountain class
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

    @Test
    void testSetTag() {
        waterFountain.setTag("Big Fountain");

        assertEquals("Big Fountain" , waterFountain.getTag());
    }

    @Test
    void testToString() {
        assertEquals("LIFE: Water Fountain: Working: ", waterFountain.toString());
    }

    @Test
    void testEquals() {
        WaterFountain fountain2 = new WaterFountain("LIFE");
        WaterFountain test = new WaterFountain("HEBB");
        WaterFountain test2 = new WaterFountain("LIFE");
        test2.setDirection("hello everybody my name is markiplier");

        assertTrue(waterFountain.equals(fountain2));

        fountain2.setStatus("Broken");
        assertFalse(waterFountain.equals(fountain2));
        assertFalse(waterFountain.equals(test));
        assertFalse(waterFountain.equals(test2));



    }

}