package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

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

        boolean success = favPins.removeAllBroken();

        assertTrue(success);
        List<Pin> favourites = favPins.searchTag("Water Fountain");

        assertEquals(0, favourites.size());
    }

    @Test
    void removeMultipleBrokenPin() {
        favPins.addPin(fountain);
        favPins.addPin(fountain2);
        favPins.addPin(fountain3);

        fountain.setStatus("Broken");
        fountain3.setStatus("Broken");

        boolean success = favPins.removeAllBroken();

        List<Pin> favourites = favPins.searchTag("Water Fountain");

        assertTrue(success);
        assertEquals(1, favourites.size());
        assertEquals(fountain3, favourites.get(0));

    }

    @Test
    void removeBrokenButNoneIsBroken() {
        favPins.addPin(fountain);
        favPins.addPin(fountain2);
        favPins.addPin(fountain3);

        boolean success = favPins.removeAllBroken();

        List<Pin> favourites = favPins.searchTag("Water Fountain");

        assertFalse(success);
        assertEquals(3, favourites.size());
        assertEquals(fountain, favourites.get(0));
        assertEquals(fountain2, favourites.get(1));
        assertEquals(fountain3, favourites.get(2));

    }


}
