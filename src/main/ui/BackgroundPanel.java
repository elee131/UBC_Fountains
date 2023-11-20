package ui;

import model.Pin;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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

        drawAdditionalImages(g, MapGUI.allPins.getAllPins(), MapGUI.pointList);

    }

    private void drawAdditionalImages(Graphics g, List<Pin> pinList, List<Point> pointList) {

        List<Point> clonedList = new ArrayList<>(pointList);

        for (Pin pin : pinList) {
            String tag = pin.getTag();
            int posX = (int) clonedList.get(0).getX();
            int posY = (int) clonedList.get(0).getY();
            int imageX = (int) (posX - waterIcon.getWidth(this) / 2.0);
            int imageY = (int) (posY - waterIcon.getHeight(this) / 2.0);

            if (tag.equals("Water Fountain")) {
                g.drawImage(waterIcon, imageX, imageY, this);
            } else {

                g.drawImage(pinIcon, imageX, imageY, this);
            }

            clonedList.remove(0);

        }

    }

    public void updateMapImage(List<Pin> pinList, List<Point> pointList) {

        if (bgImage != null) {
            Graphics2D g2d = (Graphics2D) this.getGraphics();
            g2d.drawImage(bgImage, 0, 0, this);
            drawAdditionalImages(g2d, pinList, pointList);
            g2d.dispose();

            repaint();
        }

    }





}
