package ui;

import model.Pin;
import model.UserPin;
import model.WaterFountain;
import static ui.MapGUI.container;


import javax.swing.*;
import javax.swing.JOptionPane;


public class PinEditPopup extends JOptionPane {


    JTextField tag;
    JTextField location;
    JTextField status;
    JTextField direction;
    int option;


    // REQUIRES: string can only be one of "waterFountain" or "userPin"
    public PinEditPopup(String str) {

        if (str.equals("waterFountain")) {
            tag = new JTextField("Water Fountain");
        } else {
            tag = new JTextField();
        }
        location = new JTextField();
        status = new JTextField();
        direction = new JTextField();

        Object[] textFields = {
                "Tag: ", tag,
                "Location: ", location,
                "Status: ", status,
                "Direction: ", direction
        };

        option = JOptionPane.showConfirmDialog(container, textFields, "Create new Pin", JOptionPane.OK_CANCEL_OPTION);

        if (option == 0) {
            createWaterFountain();
        } else if (option == 1) {
            createUserPin();
        }


    }

    public PinEditPopup(UserPin pin) {
        tag = new JTextField(pin.getTag());
        location = new JTextField(pin.getLocation());
        status = new JTextField(pin.getStatus());
        direction = new JTextField(pin.getDirections());

        Object[] textFields = {
                "Tag: ", tag,
                "Location: ", location,
                "Status: ", status,
                "Direction: ", direction
        };

        container = new JFrame();
        container.setSize(300, 300);
        option = JOptionPane.showConfirmDialog(container, textFields, "Edit Pin", JOptionPane.OK_CANCEL_OPTION);

        editUserPin(pin);
    }

    public PinEditPopup(WaterFountain pin) {
        location = new JTextField(pin.getLocation());
        status = new JTextField(pin.getStatus());
        direction = new JTextField(pin.getDirections());

        Object[] textFields = {
                "Location: ", location,
                "Status: ", status,
                "Direction: ", direction
        };

        container = new JFrame();
        container.setSize(300, 300);
        option = JOptionPane.showConfirmDialog(container, textFields, "Edit Fountain", JOptionPane.OK_CANCEL_OPTION);

        editWaterFountain(pin);
    }



    public void editUserPin(Pin pin) {

        pin.setTag(tag.getText());
        pin.setLocation(location.getText());
        pin.setStatus(status.getText());
        pin.setDirection(direction.getText());

    }

    public void editWaterFountain(Pin pin) {

        pin.setLocation(location.getText());
        pin.setStatus(status.getText());
        pin.setDirection(direction.getText());

    }

    public void createUserPin() {

        UserPin newPin = new UserPin(tag.getText(), location.getText());
        newPin.setStatus(status.getText());
        newPin.setDirection(direction.getText());
        MapGUI.allPins.addPin(newPin);
        //MapGUI.pinPoints.put


    }

    public void createWaterFountain() {
        WaterFountain wt = new WaterFountain(location.getText());
        wt.setStatus(status.getText());
        wt.setDirection(direction.getText());
        MapGUI.allPins.addPin(wt);


    }


}


