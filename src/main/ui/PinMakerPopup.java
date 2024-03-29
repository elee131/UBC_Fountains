package ui;

import javax.swing.*;

import java.awt.*;

// code inspired from https://www.youtube.com/watch?v=_hB94F8JioM
// represents the popup screen that prompts the user to create a pin or cancel
public class PinMakerPopup {

    int option;

    JOptionPane pinMakerScreen;

    Object[] pinOptions = {
            "Mark a water fountain!",
            "Mark a pin!",
            "Cancel"
    };

    public PinMakerPopup(Point point) {
        JFrame container = new JFrame();
        pinMakerScreen = new JOptionPane();
        container.setSize(300,300);
        option = pinMakerScreen.showOptionDialog(container,
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
