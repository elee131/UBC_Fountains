package ui;

import model.Pin;
import model.UserPin;
import model.WaterFountain;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents the panel shown when selecting a pin
public class PinsPanel extends  JPanel {
    Pin madePin;

    JButton userPin;
    JButton waterFountain;

    JButton submit;
    JTextField location;
    JTextField tag;
    JTextField direction;
    JTextField status;



    // TODO represents the panel shown when a pin is clicked on
    public PinsPanel() {
        setVisible(true);
        userPin = new JButton("Your own pin");
        buildUserPinPanel(userPin);

        waterFountain = new JButton();
        buildWaterFountainPanel(waterFountain);

    }

    private void buildUserPinPanel(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tag = new JTextField();
                location = new JTextField();
                status = new JTextField();
                direction = new JTextField();

                submit = new JButton("submit");
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        waterFountain = new JButton("Water fountain");
                        madePin = new UserPin(location.getText(), tag.getText());
                        madePin.setStatus(status.getText());
                        madePin.setDirection(direction.getText());
                        MapGUI.allPins.addPin(madePin);
                    }
                });
            }
        });
        setVisible(true);
    }

    private void buildWaterFountainPanel(JButton button) {
        setVisible(true);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                location = new JTextField();
                status = new JTextField();
                direction = new JTextField();

                submit = new JButton("submit");
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        waterFountain = new JButton("Water fountain");
                        madePin = new WaterFountain(location.getText());
                        madePin.setStatus(status.getText());
                        madePin.setDirection(direction.getText());
                        MapGUI.allPins.addPin(madePin);

                    }
                });
            }
        });
    }



    public PinsPanel(Pin pin) {
        tag = new JTextField(pin.getTag());
        location = new JTextField(pin.getLocation());
        status = new JTextField(pin.getStatus());
        direction = new JTextField(pin.getDirections());

        submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                waterFountain = new JButton("Water fountain");
                madePin = new WaterFountain(location.getText());
                madePin.setStatus(status.getText());
                madePin.setDirection(direction.getText());
                MapGUI.allPins.addPin(madePin);

            }
        });

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

    // TODO return true if user has clicked the confirm button to make the pin in given location
    public boolean constructed() {
        return false;
    }

    // todo -- return the pin made from the user action
    public Pin getPin() {
        return madePin;
    }


}
