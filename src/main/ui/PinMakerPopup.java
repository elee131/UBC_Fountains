package ui;

import javax.swing.*;

import java.awt.*;



public class PinMakerPopup {

    int option;

    JOptionPane pinMakerScreen;

    Object[] pinOptions = {
            "Mark a water fountain!",
            "Mark a pin!",
            "Cancel"
    };

    public PinMakerPopup(Point point, MapGUI mapGUI) {
        JFrame container = new JFrame();
        pinMakerScreen = new JOptionPane();
        container.setSize(300,300);
        option = pinMakerScreen.showOptionDialog(mapGUI,
                "What Type of Pin Would You Like?",
                "Create A Pin!",
                pinMakerScreen.YES_NO_CANCEL_OPTION,
                pinMakerScreen.QUESTION_MESSAGE,
                null,
                pinOptions,
                pinOptions[2]);

        if (option == 0) {
            new PinEditPopup("waterFountain", point);
        } else if (option == 1) {
            new PinEditPopup("userPin", point);
        } else if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
            //
        }



    }

    public JOptionPane getPinMakerScreen() {
        return pinMakerScreen;
    }
}
