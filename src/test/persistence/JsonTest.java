package persistence;

import model.Pin;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Code influenced by the JsonSerizalizationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// a test class for testing each pin 's attributes
public class JsonTest {
    protected void checkPin(String location,String tag, String status, String direction, Pin pin) {
        assertEquals(location, pin.getLocation());
        assertEquals(tag, pin.getTag());
        assertEquals(direction, pin.getDirections());
        assertEquals(status, pin.getStatus());
    }
}
