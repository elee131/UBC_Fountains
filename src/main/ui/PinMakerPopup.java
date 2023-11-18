package ui;

import javax.swing.*;

import static ui.MapGUI.container;

public class PinMakerPopup extends JOptionPane {

    int option;

    Object[] pinOptions = {
            "Mark a water fountain!",
            "Mark a pin!",
            "Cancel"
    };

    public PinMakerPopup() {
        container.setSize(300,300);
        option = JOptionPane.showOptionDialog(container,
                "What Type of Pin Would You Like?",
                "Create A Pin!",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                pinOptions,
                pinOptions[2]);

        if (option == 0) {
            new PinEditPopup("waterFountain");
        } else if (option == 1) {
            new PinEditPopup("userPin");
        }
    }

}
