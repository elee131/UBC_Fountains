package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AllPinsTest {

    Pin fountain;
    Pin fountain2;
    Pin fountain3;

    Pin coffeePlace;
    Pin lunchSpot;

    AllPins allPins;

    @BeforeEach
    void runBefore(){
        fountain = new WaterFountain("ICCS");
        fountain2 = new WaterFountain("ESB");
        fountain3 = new WaterFountain("CHEM");
        coffeePlace = new UserPin("Main Mall", "Coffee");
        lunchSpot = new UserPin("Rose Garden", "Lounge");

        allPins = new AllPins();
    }

    @Test
    void testConstructor() {

        List<Pin> fountains = allPins.searchTag("Water Fountain");
        assertTrue(fountains.isEmpty());
    }

    @Test
    void testAddPin(){
        boolean success = allPins.addPin(fountain);

        assertTrue(success);
        List<Pin> fountains = allPins.searchTag("Water Fountain");
        assertEquals(1, fountains.size());
        assertEquals(fountain, fountains.get(0));
    }

    @Test
    void testAddMultiplePins() {
        List<Pin> fountains = allPins.searchTag("Water Fountain");

        allPins.addPin(fountain);
        boolean success = allPins.addPin(fountain2);

        assertTrue(success);
        assertEquals(1, fountains.size());
        assertEquals(fountain, fountains.get(0));
        assertEquals(fountain2, fountains.get(0));



    }

    @Test
    void testAddDuplicate() {
        List<Pin> fountains = allPins.searchTag("Water Fountain");
        boolean success = allPins.addPin(fountain);
        allPins.addPin(fountain);

        assertFalse(success);

        assertEquals(1, fountains.size());
        assertEquals(fountain, fountains.get(0));


    }


    @Test
    void testSearchLocation(){
        allPins.addPin(fountain);
        allPins.addPin(fountain2);
        allPins.addPin(fountain3);

        List<Pin> chemFountains = allPins.searchLocation("CHEM");
        assertEquals(1, chemFountains.size());
        assertEquals(fountain3, chemFountains.get(0));


    }

    @Test
    void testSearchTag() {

        allPins.addPin(lunchSpot);
        allPins.addPin(fountain);
        allPins.addPin(fountain2);

        List<Pin> loungeSpots = allPins.searchTag("Lounge");

        assertEquals(1, loungeSpots.size());
        assertEquals(lunchSpot, loungeSpots.get(0));

    }



    @Test
    void testRemovePin() {
        allPins.addPin(fountain);
        boolean success = allPins.removePin(fountain);

        assertTrue(success);

        List<Pin> pins = allPins.searchTag("Water Fountain");

        assertEquals(0, pins.size());
    }

    @Test
    void removeMultiple() {
        allPins.addPin(fountain);
        allPins.addPin(fountain2);
        allPins.addPin(fountain3);

        boolean success = allPins.removePin(fountain);
        boolean success2 = allPins.removePin(fountain2);

        assertTrue(success);
        assertTrue(success2);

        List<Pin> pins = allPins.searchTag("Water Fountain");

        assertEquals(1, pins.size());
        assertEquals(fountain3, pins.get(0));

    }

    @Test
    void testRemoveNonExistentPin() {
        allPins.addPin(fountain);
        boolean success = allPins.removePin(fountain2);

        assertFalse(success);

    }


}
