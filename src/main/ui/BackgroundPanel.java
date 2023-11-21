package ui;

import model.Pin;
import model.UserPin;
import model.WaterFountain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

// represents the panel in the background with the image of the map with images of the pins
public class BackgroundPanel extends JComponent implements MouseListener {
    private final Image bgImage;
    private final  Image pinIcon;
    private final  Image waterIcon;

    private List<Pin> pinList;
    private List<Point> pointList;

    public BackgroundPanel(Image bgImage, Image pinIcon, Image waterIcon) {

        this.bgImage = bgImage;
        this.pinIcon = pinIcon;
        this.waterIcon = waterIcon;
        pinList = new ArrayList<>();
        pointList = new ArrayList<>();

        this.addMouseListener(this);
    }

    public void updatePinsAndPoints(List<Pin> pinList, List<Point> pointList) {
        this.pinList = pinList;
        this.pointList = pointList;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (bgImage != null) {
            g.drawImage(bgImage, 0, 0, this);
        }

        drawAdditionalImages(g, pinList, pointList);

    }

    private void drawAdditionalImages(Graphics g, List<Pin> pinList, List<Point> pointList) {



        int listSize = pointList.size();

        for (int i = 0; i < listSize; i++) {
            Pin pin = pinList.get(i);
            String tag = pin.getTag();
            Point point = pointList.get(i);

            int posX = (int) point.getX();
            int posY = (int) point.getY();
            int imageX = (int) (posX - waterIcon.getWidth(this) / 2.0);
            int imageY = (int) (posY - waterIcon.getHeight(this) / 2.0);


            if (tag.equals("Water Fountain")) {
                g.drawImage(waterIcon, imageX, imageY, this);
            } else {
                g.drawImage(pinIcon, imageX, imageY, this);
            }
        }

    }


    // MODIFIES: this
    // EFFECTS: check if the user has clicked on a pin.
    //         if the user clicked on a pin, display a pop up with its saved information and allow them to edit it
    //         if the user has not clicked on a pin, display a pop up prompting the user to create a pin
    @Override
    public void mouseClicked(MouseEvent e) {
        Point point = e.getPoint();
        Pin selected = clickedPin(point);

        if (selected != null) {

            if (selected.getTag().equals("Water Fountain")) {
                JOptionPane editor = new PinEditPopup((WaterFountain) selected);
                this.add(editor);
            } else {
                JOptionPane editor = new PinEditPopup((UserPin) selected);
                this.add(editor);
            }
        } else {

            PinMakerPopup pinMaker = new PinMakerPopup(point);
            this.add(pinMaker.getPinMakerScreen());
        }
    }

    // EFFECTS: checks if the user has clicked on a pin. return pin if the user has, null otherwise
    public Pin clickedPin(Point p) {
        Pin selected = null;
        double posX = p.getX();
        double posY = p.getY();

        for (Point point : pointList) {
            boolean closeToX = (Math.abs(posX - point.getX()) <= 50);
            boolean closeToY = (Math.abs(posY - point.getY()) <= 50);

            if (closeToX && closeToY) {
                int indexOfPin = pointList.indexOf(point);
                selected = MapGUI.allPins.getAllPins().get(indexOfPin);
            }
        }

        return selected; // stub
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
