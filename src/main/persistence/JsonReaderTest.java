package persistence;

import model.Map;
import model.Pin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @BeforeEach
    void runBefore() {

    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noFile.json");
        try {
            Map map = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMap() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMap.json");

        try {
            Map map = reader.read();
            assertEquals("My Map", map.getName());
            assertEquals(0, map.numFavs());
            assertEquals(0, map.numAll());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMap() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMap.json");
        try {
            Map map = reader.read();
            assertEquals("My New Map", map.getName());
            List<Pin> mapAllPins = map.getAllPins();
            List<Pin> mapFavPins = map.getFavPins();

            assertEquals(2, mapAllPins.size());
            assertEquals(1,mapFavPins.size());
            checkPin("ICCS", "Food", "Working","", mapAllPins.get(0));
            checkPin("CIRS", "Water Fountain", "Working","", mapAllPins.get(1));
            checkPin("CIRS", "Water Fountain", "Working","", mapFavPins.get(0));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
