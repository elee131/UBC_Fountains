package ui;

import model.Map;

import javax.swing.*;

// represents the panel shown when selecting a pin
public class PinsPanel extends  JPanel {

    private ImageIcon waterFountain;
    private ImageIcon pin;

    // TODO represents the panel shown when a pin is clicked on
    public PinsPanel(Map map) {
        // somehow use map to get the pins ?? idk
        // uuhhh what do i dooo
    }

    // TODO represents the edit button that lets you edit info
    public void editButton() {
        // stub
    }

    // TODO represents the window that pops up when you are trying to edit pins
    // textbox for location, tag, status, fav/noFav status, cancel button
    public void editPanel() {
        // stub
    }

    // TODO a dropdown for the user to select the status out of 4 valid choices
    public void statusDropdown() {

    }

    // TODO make a clickable star button that adds/unadds from fav
    public void addOrRemoveFav() {

    }




    // TODO import images for the gui
    private void loadImages() {
        String sep = System.getProperty("file.separator");

        waterFountain = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "yellow.png");
    }
}
