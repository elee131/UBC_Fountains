package model;

public interface Pin {

    // REQUIRES: Status must be valid( one from broken, unavailable, working, available)
    // MODIFIES: this
    // EFFECTS: change status of a pin
    void setStatus(String status);

    // EFFECTS: add the pin to favourites
    void addToFavourite();
}
