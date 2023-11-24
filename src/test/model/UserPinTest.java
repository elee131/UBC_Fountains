package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// a test class for UserPin Class
public class UserPinTest {

    UserPin userPin;


    @BeforeEach
    void runBefore() {
        userPin = new UserPin("LIFE", "Food");

    }

    @Test
    void testConstructor() {
        assertEquals("LIFE", userPin.getLocation());
        assertEquals("Food", userPin.getTag());
        assertEquals("Working", userPin.getStatus());
    }

    @Test
    void testIsBrokenFalse() {
        assertFalse(userPin.isBroken());
    }

    @Test
    void testIsBrokenTrue() {
        userPin.setStatus("Broken");
        assertTrue(userPin.isBroken());
    }

    @Test
    void testIsUnavailableFalse() {
        assertFalse(userPin.isUnavailable());

    }

    @Test
    void testIsUnavailableTrue() {
        userPin.setStatus("Unavailable");
        assertTrue(userPin.isUnavailable());
    }
    @Test
    void testSetDirection() {
        userPin.setDirection("To the right of Booster Juice");

        assertEquals("To the right of Booster Juice", userPin.getDirections());
    }

    @Test
    void testSetLocation() {
        userPin.setLocation("Heaven");

        assertEquals("Heaven", userPin.getLocation());
    }

    @Test
    void testSetTag() {
        userPin.setTag("Big Fountain");

        assertEquals("Big Fountain" , userPin.getTag());
    }

    @Test
    void testToString() {
        assertEquals("LIFE: Food: Working: ", userPin.toString());
    }

    @Test
    void testEquals() {
        UserPin test1 = new UserPin("LIFE", "Food");
        UserPin test2 = new UserPin("HEBB", "Food");
        UserPin test3 = new UserPin("LIFE", "Food");
        test3.setDirection("hello everybody my name is markiplier");


        assertTrue(userPin.equals(test1));

        test1.setStatus("Broken");
        assertFalse(userPin.equals(test1));
        assertFalse(userPin.equals(test2));
        assertFalse(userPin.equals(test3));

    }


}
