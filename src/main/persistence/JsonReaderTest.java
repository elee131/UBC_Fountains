package persistence;

import model.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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
            assertEquals("map", map.getName());


        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
