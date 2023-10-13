package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// a test for allpins class
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
        allPins.addPin(fountain);
        boolean success = allPins.addPin(fountain2);

        List<Pin> fountains = allPins.searchTag("Water Fountain");

        assertTrue(success);
        assertEquals(2, fountains.size());
        assertEquals(fountain, fountains.get(0));
        assertEquals(fountain2, fountains.get(1));


    }

    @Test
    void testAddDuplicate() {
        allPins.addPin(fountain);
        boolean success = allPins.addPin(fountain);
        List<Pin> fountains = allPins.searchTag("Water Fountain");

        assertFalse(success);

        assertEquals(1, fountains.size());
        assertEquals(fountain, fountains.get(0));
    }


    @Test
    void testSearchLocationOneFound(){
        allPins.addPin(fountain);
        allPins.addPin(fountain2);
        allPins.addPin(fountain3);

        List<Pin> chemFountains = allPins.searchLocation("CHEM");
        assertEquals(1, chemFountains.size());
        assertEquals(fountain3, chemFountains.get(0));


    }
    @Test
    void testSearchLocationMultipleFound(){
        Pin microwave = new UserPin("CHEM", "food");
        allPins.addPin(fountain);
        allPins.addPin(microwave);
        allPins.addPin(fountain3);

        List<Pin> chemPins = allPins.searchLocation("CHEM");
        assertEquals(2, chemPins.size());
        assertTrue(chemPins.contains(fountain3));
        assertTrue(chemPins.contains(microwave));
    }
    @Test
    void testSearchLocationNoneFound(){
        allPins.addPin(fountain);
        allPins.addPin(fountain2);
        allPins.addPin(fountain3);

        List<Pin> backRoomPins = allPins.searchLocation("Backrooms");
        assertEquals(0, backRoomPins.size());

    }

    @Test
    void testSearchTagOneFound() {

        allPins.addPin(lunchSpot);
        allPins.addPin(fountain);
        allPins.addPin(fountain2);

        List<Pin> loungeSpots = allPins.searchTag("Lounge");

        assertEquals(1, loungeSpots.size());
        assertEquals(lunchSpot, loungeSpots.get(0));

    }
    @Test
    void testSearchTagNoneFound() {

        allPins.addPin(lunchSpot);
        allPins.addPin(fountain);
        allPins.addPin(fountain2);

        List<Pin> eldrichSpots = allPins.searchTag("jkkgsdfg");

        assertEquals(0, eldrichSpots.size());
    }

    @Test
    void testSearchTagMultipleFound() {

        allPins.addPin(lunchSpot);
        allPins.addPin(fountain);
        allPins.addPin(fountain2);

        List<Pin> loungeSpots = allPins.searchTag("Water Fountain");

        assertEquals(2, loungeSpots.size());
    }

    @Test
    void testSearchId(){
        String id = lunchSpot.getId();
        allPins.addPin(lunchSpot);
        allPins.addPin(fountain);

        Pin matching = allPins.searchID(id);

        assertEquals(matching, lunchSpot);
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

    @Test
    void removeOnePinBroken() {
        allPins.addPin(fountain);
        fountain.setStatus("Broken");

        boolean success = allPins.removeAllUnavailable();

        assertTrue(success);
        List<Pin> favourites = allPins.searchTag("Water Fountain");

        assertEquals(0, favourites.size());
    }

    @Test
    void removeMultipleBrokenPin() {
        allPins.addPin(fountain);
        allPins.addPin(fountain2);
        allPins.addPin(fountain3);
        allPins.addPin(coffeePlace);

        fountain.setStatus("Broken");
        fountain3.setStatus("Broken");

        boolean success = allPins.removeAllUnavailable();

        List<Pin> all = allPins.getAllPins();

        assertTrue(success);
        assertEquals(2, all.size());
        assertTrue(all.contains(fountain2));
        assertTrue(all.contains(coffeePlace));

    }

    @Test
    void removeBrokenPinAndAllIsBroken() {
        allPins.addPin(fountain);
        allPins.addPin(fountain2);
        allPins.addPin(fountain3);

        fountain.setStatus("Broken");
        fountain2.setStatus("Broken");
        fountain3.setStatus("Broken");
        coffeePlace.setStatus("Unavailable");

        boolean success = allPins.removeAllUnavailable();

        List<Pin> all = allPins.getAllPins();

        assertTrue(success);
        assertEquals(0, all.size());

    }

    @Test
    void removeBrokenButNoneIsBroken() {
        allPins.addPin(fountain);
        allPins.addPin(fountain2);
        allPins.addPin(fountain3);

        boolean success = allPins.removeAllUnavailable();

        List<Pin> favourites = allPins.searchTag("Water Fountain");

        assertFalse(success);
        assertEquals(3, favourites.size());
        assertEquals(fountain, favourites.get(0));
        assertEquals(fountain2, favourites.get(1));
        assertEquals(fountain3, favourites.get(2));
    }

    @Test
    void testSetLocation() {
        fountain.setLocation("Heaven");

        assertEquals("Heaven", fountain.getLocation());
    }

    @Test
    void testSetTag() {
        fountain.setTag("Big Fountain");

        assertEquals("Big Fountain" , fountain.getTag());
    }

    @Test
    void testGetAllPins(){
        allPins.addPin(fountain);
        List<Pin> all = allPins.getAllPins();

        assertEquals(1, all.size());
        assertEquals(fountain, all.get(0));
    }


}
