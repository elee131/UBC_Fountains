package persistence;

import model.Pin;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Code influenced by the JsonSerizalizationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// a test class for testing each pin 's attributes, and testing each point attribute
public class JsonTest {
    protected void checkPin(String location,String tag, String status, String direction, Pin pin) {
        assertEquals(location, pin.getLocation());
        assertEquals(tag, pin.getTag());
        assertEquals(direction, pin.getDirections());
        assertEquals(status, pin.getStatus());
    }


    protected void checkPoint(int posX, int posY, Point point) {
        assertEquals(posX,point.getX());
        assertEquals(posY, point.getY());
    }
}
