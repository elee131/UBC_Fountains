package ui;

import model.Pin;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

// represents the panel in the background with the image of the map with images of the pins
public class BackgroundPanel extends JComponent {
    private Image bgImage;
    private Image pinIcon;
    private Image waterIcon;

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

        drawAdditionalImages(g, MapGUI.pinPoints);

    }

    private void drawAdditionalImages(Graphics g, Map<Point, Pin> pointPinMap) {

        for (Point point : pointPinMap.keySet()) {
            if (pointPinMap.get(point).getTag().equals("Water Fountain")) {
                g.drawImage(waterIcon, (int) point.getX(), (int) point.getY(), this);
            } else {
                g.drawImage(pinIcon, (int) point.getX(), (int) point.getY(), this);
            }

        }

    }

    public void updateMapImage(Map<Point, Pin> pointPinMap) {

        if (bgImage != null) {
            Graphics2D g2d = (Graphics2D) this.getGraphics();
            g2d.drawImage(bgImage, 0, 0, this);
            drawAdditionalImages(g2d, pointPinMap);
            g2d.dispose();

            repaint(); // Trigger a repaint to reflect the changes
        }

    }





}
