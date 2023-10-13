package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

// a test for favouritePins class
public class FavouritePinsTest {
    Pin fountain;
    Pin fountain2;
    Pin fountain3;

    Pin collegia;

    FavouritePins favPins;

    @BeforeEach
    void runBefore(){
        fountain = new WaterFountain("ICCS");
        fountain2 = new WaterFountain("ESB");
        fountain3 = new WaterFountain("CHEM");
        collegia = new UserPin("LIFE", "Lounge");

        favPins = new FavouritePins();

    }

    @Test
    void testConstructor() {
        List<Pin> favourites = favPins.searchLocation("ICCS");

        assertTrue(favourites.isEmpty());
    }


    @Test
    void removeOnePinBroken() {
        favPins.addPin(fountain);
        fountain.setStatus("Broken");

        boolean success = favPins.removeAllUnavailable();

        assertTrue(success);
        List<Pin> favourites = favPins.searchTag("Water Fountain");

        assertEquals(0, favourites.size());
    }

    @Test
    void removeMultipleBrokenPin() {
        favPins.addPin(fountain);
        favPins.addPin(fountain2);
        favPins.addPin(fountain3);
        favPins.addPin(collegia);

        fountain.setStatus("Broken");
        fountain3.setStatus("Broken");

        boolean success = favPins.removeAllUnavailable();

        List<Pin> favourites = favPins.getFavPins();

        assertTrue(success);
        assertEquals(2, favourites.size());
        assertTrue(favourites.contains(fountain2));
        assertTrue(favourites.contains(collegia));

    }
    @Test
    void removeBrokenPinAndAllIsBroken() {
        favPins.addPin(fountain);
        favPins.addPin(fountain2);
        favPins.addPin(fountain3);
        favPins.addPin(collegia);

        fountain.setStatus("Broken");
        fountain2.setStatus("Broken");
        fountain3.setStatus("Broken");
        collegia.setStatus("Unavailable");

        boolean success = favPins.removeAllUnavailable();

        List<Pin> favourites = favPins.searchTag("Water Fountain");

        assertTrue(success);
        assertEquals(0, favourites.size());

    }

    @Test
    void removeBrokenButNoneIsBroken() {
        favPins.addPin(fountain);
        favPins.addPin(fountain2);
        favPins.addPin(fountain3);

        boolean success = favPins.removeAllUnavailable();

        List<Pin> favourites = favPins.searchTag("Water Fountain");

        assertFalse(success);
        assertEquals(3, favourites.size());
        assertEquals(fountain, favourites.get(0));
        assertEquals(fountain2, favourites.get(1));
        assertEquals(fountain3, favourites.get(2));

    }

    @Test
    void testAddPin(){
        boolean success = favPins.addPin(fountain);

        assertTrue(success);
        List<Pin> fountains = favPins.searchTag("Water Fountain");
        assertEquals(1, fountains.size());
        assertEquals(fountain, fountains.get(0));
    }

    @Test
    void testAddMultiplePins() {
        favPins.addPin(fountain);
        boolean success = favPins.addPin(fountain2);

        List<Pin> fountains = favPins.searchTag("Water Fountain");

        assertTrue(success);
        assertEquals(2, fountains.size());
        assertEquals(fountain, fountains.get(0));
        assertEquals(fountain2, fountains.get(1));


    }

    @Test
    void testAddDuplicate() {
        favPins.addPin(fountain);
        boolean success = favPins.addPin(fountain);
        List<Pin> fountains = favPins.searchTag("Water Fountain");

        assertFalse(success);

        assertEquals(1, fountains.size());
        assertEquals(fountain, fountains.get(0));
    }


    @Test
    void testSearchLocationOneFound(){
        favPins.addPin(fountain);
        favPins.addPin(fountain2);
        favPins.addPin(fountain3);

        List<Pin> chemFountains = favPins.searchLocation("CHEM");
        assertEquals(1, chemFountains.size());
        assertEquals(fountain3, chemFountains.get(0));


    }
    @Test
    void testSearchLocationMultipleFound(){
        Pin microwave = new UserPin("CHEM", "food");
        favPins.addPin(fountain);
        favPins.addPin(microwave);
        favPins.addPin(fountain3);

        List<Pin> chemPins = favPins.searchLocation("CHEM");
        assertEquals(2, chemPins.size());
        assertTrue(chemPins.contains(fountain3));
        assertTrue(chemPins.contains(microwave));
    }
    @Test
    void testSearchLocationNoneFound(){
        favPins.addPin(fountain);
        favPins.addPin(fountain2);
        favPins.addPin(fountain3);

        List<Pin> backRoomPins = favPins.searchLocation("Backrooms");
        assertEquals(0, backRoomPins.size());

    }

    @Test
    void testSearchTagOneFound() {

        favPins.addPin(collegia);
        favPins.addPin(fountain);
        favPins.addPin(fountain2);

        List<Pin> loungeSpots = favPins.searchTag("Lounge");

        assertEquals(1, loungeSpots.size());
        assertEquals(collegia, loungeSpots.get(0));

    }
    @Test
    void testSearchTagNoneFound() {

        favPins.addPin(fountain);
        favPins.addPin(fountain);
        favPins.addPin(fountain2);

        List<Pin> eldrichSpots = favPins.searchTag("jkkgsdfg");

        assertEquals(0, eldrichSpots.size());
    }

    @Test
    void testSearchTagMultipleFound() {

        favPins.addPin(collegia);
        favPins.addPin(fountain);
        favPins.addPin(fountain2);

        List<Pin> loungeSpots = favPins.searchTag("Water Fountain");

        assertEquals(2, loungeSpots.size());
    }

    @Test
    void testSearchId(){
        String id = collegia.getId();
        favPins.addPin(collegia);
        favPins.addPin(fountain);

        Pin matching = favPins.searchID(id);

        assertEquals(matching, collegia);
    }



    @Test
    void testRemovePin() {
        favPins.addPin(fountain);
        boolean success = favPins.removePin(fountain);

        assertTrue(success);

        List<Pin> pins = favPins.searchTag("Water Fountain");

        assertEquals(0, pins.size());
    }

    @Test
    void removeMultiple() {
        favPins.addPin(fountain);
        favPins.addPin(fountain2);
        favPins.addPin(fountain3);

        boolean success = favPins.removePin(fountain);
        boolean success2 = favPins.removePin(fountain2);

        assertTrue(success);
        assertTrue(success2);

        List<Pin> pins = favPins.searchTag("Water Fountain");

        assertEquals(1, pins.size());
        assertEquals(fountain3, pins.get(0));

    }

    @Test
    void testRemoveNonExistentPin() {
        favPins.addPin(fountain);
        boolean success = favPins.removePin(fountain2);

        assertFalse(success);
    }

    @Test
    void testGetFavPins(){
        favPins.addPin(fountain);
        List<Pin> favourites = favPins.getFavPins();

        assertEquals(1, favourites.size());
        assertEquals(fountain, favourites.get(0));
    }

}
