package ui;

import model.Pin;
import model.UserPin;
import model.WaterFountain;


import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;

import static ui.MapGUI.*;

// represents the popup when creating or editing a pin
public class PinEditPopup extends JOptionPane {


    JTextField tag;
    JTextField location;
    JTextField status;
    JTextField direction;
    JRadioButton deleteButton;
    JRadioButton favouriteButton;

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
                "Direction: ", direction,
                favouriteButton
        };

        option = JOptionPane.showConfirmDialog(container, textFields, "Create new Pin", JOptionPane.OK_CANCEL_OPTION);

        if (str.equals("waterFountain")) {
            createWaterFountain(point);
        } else  {
            createUserPin(point);
        }

        container.dispose();
    }

    public PinEditPopup(UserPin pin) {
        tag = new JTextField(pin.getTag());
        location = new JTextField(pin.getLocation());
        status = new JTextField(pin.getStatus());
        direction = new JTextField(pin.getDirections());
        deleteButton = new JRadioButton("Delete pin?");
        favouriteButton = new JRadioButton("Add to favourite?");

        Object[] textFields = {
                "Tag: ", tag,
                "Location: ", location,
                "Status: ", status,
                "Direction: ", direction,
                deleteButton,
                favouriteButton

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
        deleteButton = new JRadioButton("Delete pin?");
        favouriteButton = new JRadioButton("Add to favourite?");

        Object[] textFields = {
                "Location: ", location,
                "Status: ", status,
                "Direction: ", direction,
                deleteButton,
                favouriteButton
        };

        container = new JFrame();
        container.setSize(300, 300);
        option = JOptionPane.showConfirmDialog(container, textFields, "Edit Fountain", JOptionPane.OK_CANCEL_OPTION);

        editWaterFountain(pin);

        container.dispose();

    }

    // MODIFIES: pin
    // EFFECTS: edits the given pin based on user input
    public void editUserPin(Pin pin) {
        if (deleteButton.isSelected()) {
            int index = allPins.getAllPins().indexOf(pin);
            allPins.removePin(pin);
            pointList.remove(index);
        } else if (favouriteButton.isSelected()) {
            MapGUI.favPins.addPin(pin);
        }

        pin.setTag(tag.getText());
        pin.setLocation(location.getText());
        pin.setStatus(status.getText());
        pin.setDirection(direction.getText());

    }

    // MODIFIES: pin
    // EFFECTS: edits the given pin based on user input except tag
    public void editWaterFountain(Pin pin) {
        if (deleteButton.isSelected()) {
            int index = allPins.getAllPins().indexOf(pin);
            allPins.removePin(pin);
            pointList.remove(index);
        } else if (favouriteButton.isSelected()) {
            MapGUI.favPins.addPin(pin);
        }

        pin.setLocation(location.getText());
        pin.setStatus(status.getText());
        pin.setDirection(direction.getText());

    }

    // MODIFIES: MapGUI, pin
    // EFFECTS: creates new userpin based on user input
    public void createUserPin(Point point) {
        UserPin newPin = new UserPin(tag.getText(), location.getText());
        newPin.setStatus(status.getText());
        newPin.setDirection(direction.getText());

        MapGUI.allPins.addPin(newPin);
        if (favouriteButton.isSelected()) {
            MapGUI.favPins.addPin(newPin);
        }

        MapGUI.pointList.add(point);
        System.out.println(newPin.getTag());
        MapGUI.background.updatePinsAndPoints(allPins.getAllPins(), MapGUI.pointList);


    }

    // MODIFIES: MapGUI, pin
    // EFFECTS: creates new waterFountain based on user input
    public void createWaterFountain(Point point) {
        WaterFountain wt = new WaterFountain(location.getText());
        wt.setStatus(status.getText());
        wt.setDirection(direction.getText());
        MapGUI.allPins.addPin(wt);
        MapGUI.pointList.add(point);

        if (favouriteButton.isSelected()) {
            MapGUI.favPins.addPin(wt);
        }

        MapGUI.background.updatePinsAndPoints(allPins.getAllPins(), MapGUI.pointList);

    }


}


