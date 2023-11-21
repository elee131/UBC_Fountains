package persistence;

import model.Map;
import model.Pin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// a test class for JsonReader class
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
            assertEquals(0, map.getAllPoints().size());
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
            List<Point> mapAllPoints = map.getAllPoints();

            assertEquals(2, mapAllPins.size());
            assertEquals(2,mapFavPins.size());
            checkPin("ICCS", "Food", "Working","", mapAllPins.get(0));
            checkPin("CIRS", "Water Fountain", "Working","", mapAllPins.get(1));
            checkPin("CIRS", "Water Fountain", "Working","", mapFavPins.get(0));
            checkPin("ICCS", "Food", "Working","", mapFavPins.get(1));

            checkPoint(300,400,mapAllPoints.get(0));
            checkPoint(600,700,mapAllPoints.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
