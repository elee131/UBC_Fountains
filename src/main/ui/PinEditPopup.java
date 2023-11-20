package ui;

import model.Pin;
import model.UserPin;
import model.WaterFountain;

import static ui.MapGUI.allPins;
import static ui.MapGUI.container;


import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;

// represents the popup when creating or editing a pin
public class PinEditPopup extends JOptionPane {


    JTextField tag;
    JTextField location;
    JTextField status;
    JTextField direction;
    int option;


    // REQUIRES: string can only be one of "waterFountain" or "userPin"
    public PinEditPopup(String str, Point point) {

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
            createWaterFountain(point);
        } else if (option == 1) {
            createUserPin(point);
        } else {
            container.dispose();
        }

        container.dispose();
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

        option = JOptionPane.showConfirmDialog(container, textFields, "Edit Pin", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            editUserPin(pin);
        } else {
            container.dispose();
        }
        
        container.dispose();
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

        container.dispose();

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

    public void createUserPin(Point point) {

        UserPin newPin = new UserPin(tag.getText(), location.getText());
        newPin.setStatus(status.getText());
        newPin.setDirection(direction.getText());
        MapGUI.allPins.addPin(newPin);
        MapGUI.pointList.add(point);
        MapGUI.background.updateMapImage(allPins.getAllPins(), MapGUI.pointList);


    }

    public void createWaterFountain(Point point) {
        WaterFountain wt = new WaterFountain(location.getText());
        wt.setStatus(status.getText());
        wt.setDirection(direction.getText());
        MapGUI.allPins.addPin(wt);
        MapGUI.pointList.add(point);
        MapGUI.background.updateMapImage(allPins.getAllPins(), MapGUI.pointList);

    }


}


