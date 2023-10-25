package persistence;

import model.Pin;
import model.Map;
import model.UserPin;
import model.WaterFountain;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Map map = new Map("My Map");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMap() {
        try {
            Map map = new Map("My Map");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMap.json");
            writer.open();
            writer.write(map);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMap.json");
            map = reader.read();
            assertEquals("My Map", map.getName());
            // assertEquals(0, wr.numThingies()); check fav things
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterGeneralMap() {
        try {
            Map map = new Map("My New Map");
            map.addPinToAll(new UserPin("ICCS","Food"));
            map.addPinToAll(new WaterFountain("CIRS"));
            map.addPinToFav(new WaterFountain("CIRS"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMap.json");
            writer.open();
            writer.write(map);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMap.json");
            map = reader.read();
            assertEquals("My New Map", map.getName());
            List<Pin> mapAllPins = map.getAllPins();
            List<Pin> mapFavPins = map.getFavPins();

            assertEquals(2, mapAllPins.size());
            assertEquals(1, mapFavPins.size());

            checkPin("ICCS", "Food","Working", "", mapAllPins.get(0));
            checkPin("CIRS","Water Fountain", "Working", "", mapAllPins.get(1));
            checkPin("CIRS","Water Fountain", "Working","", mapFavPins.get(0));


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterUpdateMap() {
        List<Pin> pinList = createPinList();

        try {
            Map map = new Map("My New Map");
            map.updateAllToList(pinList);
            map.updateFavToList(pinList);

            JsonWriter writer = new JsonWriter("./data/testWriterUpdateMap.json");
            writer.open();
            writer.write(map);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterUpdateMap.json");
            map = reader.read();
            assertEquals("My New Map", map.getName());
            List<Pin> mapAllPins = map.getAllPins();
            List<Pin> mapFavPins = map.getFavPins();

            assertEquals(2, mapAllPins.size());
            assertEquals(2, mapFavPins.size());

            checkPin("ICCS", "Food","Unavailable", "", mapAllPins.get(0));
            checkPin("CIRS","Water Fountain", "Working", "", mapAllPins.get(1));
            checkPin("ICCS", "Food","Unavailable", "", mapFavPins.get(0));
            checkPin("CIRS","Water Fountain", "Working","", mapFavPins.get(1));



        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    private List<Pin> createPinList() {
        List<Pin> pinList = new ArrayList<>();

        Pin csFood = new UserPin("ICCS", "Food");
        csFood.setStatus("Unavailable");
        pinList.add(csFood);
        pinList.add(new WaterFountain("CIRS"));



        return pinList;
    }


}
