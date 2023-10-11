package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AllPinsTest {

    Pin fountain;
    Pin fountain2;
    Pin coffeePlace;
    Pin lunchSpot;

    AllPins allPins;

    @BeforeEach
    void runBefore(){
        fountain = new WaterFountain("ICCS");
        fountain2 = new WaterFountain("ESB");
        coffeePlace = new UserPin("Main Mall", "coffee");
        lunchSpot = new UserPin("Rose Garden", "lounge");

        allPins = new AllPins();
    }

    @Test
    void testConstructor() {
        assertEquals(0, allPins.getSize());
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
        allPins.addPin(fountain);
        boolean success = allPins.addPin(fountain2);

        assertTrue(success);



    }

    @Test
    void testAddDuplicate() {
        allPins.addPin(fountain);

    }


}
