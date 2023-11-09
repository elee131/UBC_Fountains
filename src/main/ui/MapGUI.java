package ui;

import javax.swing.*;
import java.awt.*;

// represents the main screen of the application with a map that displays pins
public class MapGUI extends JFrame {

    private PinsPanel pinsPanel;
    private MenuPanel menuPanel;

    private Image mapBackground;


    private ImageIcon waterFountainIcon;

    private ImageIcon pinIcon;
    MapApp map;

    // TODO
    // all ripped from spaceinvaders
    public MapGUI() {
        super("UBC fountains");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setUndecorated(false);
//        map = new MapApp();
//        pinsPanel = new PinsPanel(MapApp);
//        menuPanel = new MenuPanel();
//        add(pinsPanel);
//        add(menuPanel, BorderLayout.NORTH);
//        // addKeyListener(new KeyHandler());
//        pack();
//       // centreOnScreen();
//        setVisible(true);

        this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(mapBackground, 0, 0, null);
            }
        });
        pack();
        setVisible(true);
    }


    // TODO finds the place where the user clicked and decides what to do from there
    public void keyHandler() {

    }

    // TODO allows the user to navigate around map by dragging mouse cursor
    public void moveAround() {

    }


   // TODO allow user to zoom in/out by hitting zoom in/out button
    public void changeZoomLevel() {

    }

    // TODO displays pins if they should be ex. if its in fav when viewfav is selected
    // or if its part of the search results
    public void displayPin() {

    }


    // TODO display help menu somewhere on screen with instructions??
    // might not do it tbh
    public void helpMenu() {

    }


//
//    // TODO import images for the gui -- did i do it right???? idk
//    private void loadImages() {
//        String sep = System.getProperty("file.separator");
//
//        mapBackground = new Image(System.getProperty("user.dir") + sep
//                + "images" + sep + "ubc-campus-map.png");
//
//        waterFountainIcon = new ImageIcon(System.getProperty("user.dir") + sep
//                + "images" + sep + "yellow.png");
//
//        pinIcon = new ImageIcon(System.getProperty("user.dir") + sep
//                + "images" + sep + "yellow.png");
//    }

    // TODO represents the warning pop up message for the viewer when they do something thats not allowed
    // handles exceptions thrown from keyHandler mostly
    private void warningPopUp(Exception e) {

    }
}
