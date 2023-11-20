package model.pinsWithCoord;

import model.WaterFountain;

// represents a water fountain with coordinates with respect to the map
public class WaterFountainWithCoord extends WaterFountain {

    int posX;
    int posY;

    public WaterFountainWithCoord(String location, int posX, int posY) {
        super(location);
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    @Override
    public String toString() {
        return  super.toString() + posX + ": " + posY;
    }
}
