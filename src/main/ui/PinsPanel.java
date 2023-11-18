//package ui;
//
//import model.Pin;
//import model.UserPin;
//import model.WaterFountain;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//// represents the panel shown when selecting a pin
//public class PinsPanel extends  JPanel {
//    Pin madePin;
//
//    JButton userPin;
//    JButton waterFountain;
//
//    JButton submit;
//    JTextField location;
//    JTextField tag;
//    JTextField direction;
//    JTextField status;
//
//    Dimension panelSize;
//
//
//
//    // TODO represents the panel shown when a pin is clicked on
//    public PinsPanel() {
//        panelSize = new Dimension(100,100);
//        setBackground(Color.gray);
//        setPreferredSize(panelSize);
//
//        userPin = new JButton("Your own pin");
//        waterFountain = new JButton("Water Fountain!");
//
//        this.add(userPin);
//        this.add(waterFountain);
//        setVisible(true);
//
//        buildUserPinPanel(this, userPin);
//        buildWaterFountainPanel(this, waterFountain);
//
//    }
//
//    private void buildUserPinPanel(JPanel panel, JButton button) {
//        button.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                panel.remove(waterFountain);
//                panel.remove(userPin);
//
//                tag = new JTextField(16);
//                panel.add(tag, BoxLayout.Y_AXIS);
//
//                addTextFields(panel);
//                panel.revalidate();
//
//                submit.addActionListener(new ActionListener() {
//                    public void actionPerformed(ActionEvent e) {
//                        madePin = new UserPin(location.getText(), tag.getText());
//                        madePin.setStatus(status.getText());
//                        madePin.setDirection(direction.getText());
//                        MapGUI.allPins.addPin(madePin);
//
//                        panel.removeAll();
//                        panel.setEnabled(false);
//                        panel.setVisible(false);
//                        panel.revalidate();
//                    }
//                });
//            }
//        });
//        setVisible(true);
//    }
//
//    private void buildWaterFountainPanel(JPanel panel, JButton button) {
//        setVisible(true);
//
//        button.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                addTextFields(panel);
//
//                panel.remove(waterFountain);
//                panel.remove(userPin);
//
//                panel.revalidate();
//
//                submit.addActionListener(new ActionListener() {
//                    public void actionPerformed(ActionEvent e) {
//                        madePin = new WaterFountain(location.getText());
//                        madePin.setStatus(status.getText());
//                        madePin.setDirection(direction.getText());
//                        MapGUI.allPins.addPin(madePin);
//
//                        panel.removeAll();
//                        panel.setEnabled(false);
//                        panel.setVisible(false);
//                        panel.revalidate();
//
//                    }
//                });
//            }
//        });
//    }
//
//    private void addTextFields(JPanel panel) {
//        location = new JTextField("Closest Building Code to this pin", 16);
//        status = new JTextField("One of working, broken, available, unavailable", 16);
//        direction = new JTextField("enter brief directions here",20);
//
//        submit = new JButton("submit");
//
//        panel.add(location, BoxLayout.Y_AXIS);
//        panel.add(status,BoxLayout.Y_AXIS);
//        panel.add(direction, BoxLayout.Y_AXIS);
//        panel.add(submit, BoxLayout.Y_AXIS);
//    }
//
//
//
//    public PinsPanel(Pin pin) {
//        tag = new JTextField(pin.getTag());
//        location = new JTextField(pin.getLocation());
//        status = new JTextField(pin.getStatus());
//        direction = new JTextField(pin.getDirections());
//
//        submit = new JButton("Submit");
//        submit.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                waterFountain = new JButton("Water fountain");
//                madePin = new WaterFountain(location.getText());
//                madePin.setStatus(status.getText());
//                madePin.setDirection(direction.getText());
//                MapGUI.allPins.addPin(madePin);
//
//            }
//        });
//
//    }
//
//    // TODO represents the edit button that lets you edit info
//    public void editButton() {
//        // stub
//    }
//
//    // TODO represents the window that pops up when you are trying to edit pins
//    // textbox for location, tag, status, fav/noFav status, cancel button
//    public void editPanel() {
//        // stub
//    }
//
//    // TODO a dropdown for the user to select the status out of 4 valid choices
//    public void statusDropdown() {
//
//    }
//
//    // TODO make a clickable star button that adds/unadds from fav
//    public void addOrRemoveFav() {
//
//    }
//
//    // TODO return true if user has clicked the confirm button to make the pin in given location
//    public boolean constructed() {
//        return false;
//    }
//
//    // todo -- return the pin made from the user action
//    public Pin getPin() {
//        return madePin;
//    }
//
//
//}
