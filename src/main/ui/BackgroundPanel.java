package ui;

import model.Pin;
import model.pinsWithCoord.UserPinWithCoord;
import model.pinsWithCoord.WaterFountainWithCoord;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// represents the panel in the background with the image of the map with images of the pins
public class BackgroundPanel extends JComponent {
    private final Image bgImage;
    private final  Image pinIcon;
    private final  Image waterIcon;

    public BackgroundPanel(Image bgImage, Image pinIcon, Image waterIcon) {

        this.bgImage = bgImage;
        this.pinIcon = pinIcon;
        this.waterIcon = waterIcon;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (bgImage != null) {
            g.drawImage(bgImage, 0, 0, this);
        }

        drawAdditionalImages(g, MapGUI.allPins.getAllPins());

    }

    private void drawAdditionalImages(Graphics g, List<Pin> pinList) {

//        for (Point point : pointPinMap.keySet()) {
//            String tag = pointPinMap.get(point).getTag();
//            int imageX = (int) (point.getX() - waterIcon.getWidth(this) / 2.0);
//            int imageY = (int) (point.getY() - waterIcon.getHeight(this) / 2.0);
//
//            if (tag.equals("Water Fountain")) {
//                g.drawImage(waterIcon, imageX, imageY, this);
//            } else {
//
//                g.drawImage(pinIcon, imageX, imageY, this);
//            }
//        }

        for (Pin pin : pinList) {
            if (pin.getClass() == WaterFountainWithCoord.class) {
                WaterFountainWithCoord wf = (WaterFountainWithCoord) pin;
                int imageX = (int) (wf.getPosX() - waterIcon.getWidth(this) / 2.0);
                int imageY = (int) (wf.getPosY() - waterIcon.getHeight(this) / 2.0);

                g.drawImage(waterIcon, imageX, imageY, this);


            } else if (pin.getClass() == UserPinWithCoord.class) {
                UserPinWithCoord userPin = (UserPinWithCoord) pin;
                int imageX = (int) (userPin.getPosX() - pinIcon.getWidth(this) / 2.0);
                int imageY = (int) (userPin.getPosY() - pinIcon.getHeight(this) / 2.0);

                g.drawImage(pinIcon, imageX, imageY, this);
            }
        }

    }

    public void updateMapImage(List<Pin> pinList) {

        if (bgImage != null) {
            Graphics2D g2d = (Graphics2D) this.getGraphics();
            g2d.drawImage(bgImage, 0, 0, this);
            drawAdditionalImages(g2d, pinList);
            g2d.dispose();

            repaint();
        }

    }





}
