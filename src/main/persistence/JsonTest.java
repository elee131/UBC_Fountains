package persistence;

import model.Pin;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPin(String tag, String location, String direction, String status, Pin pin) {
        assertEquals(tag, pin.getTag());
        assertEquals(location, pin.getLocation());
        assertEquals(direction, pin.getDirections());
        assertEquals(status, pin.getStatus());
    }
}
