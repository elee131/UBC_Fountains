package ui;

import model.AllPins;
import model.FavouritePins;
import model.Map;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// TODO - bring in every functionality from mapApp into MapGUI
// represents the main screen of the application with a map that displays pins
public class MapGUI extends JFrame {

    private static final int INITIAL_SCREEN_WIDTH = 1500;
    public static final int INITIAL_SCREEN_HEIGHT = 1000;

    boolean isActive;
    private PinsPanel pinsPanel;
    private MenuPanel menuPanel;

    private ImageIcon mapBackground;
    private ImageIcon waterFountainIcon;

    private ImageIcon pinIcon;
    private Map myMap;

    private AllPins allPins;

    private FavouritePins favPins;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private static final String JSON_STORE = "./data/myMap.json";

    private JLabel myLabel;
    private JPanel bgPanel;



    // TODO
    // all ripped from spaceinvaders
    public MapGUI() {
        super("UBC fountains");
        setSize(INITIAL_SCREEN_WIDTH, INITIAL_SCREEN_HEIGHT);

        loadImages();
//        myLabel = new JLabel(mapBackground);
//        add(myLabel);
        bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Image img = mapBackground.getImage();
                img.getScaledInstance(INITIAL_SCREEN_WIDTH, INITIAL_SCREEN_HEIGHT, img.SCALE_SMOOTH);

                setLayout(null);
                g.drawImage(img, 0, 0,INITIAL_SCREEN_WIDTH, INITIAL_SCREEN_HEIGHT, null);
            }
        };

        this.add(bgPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setVisible(true);

        allPins = new AllPins();
        favPins = new FavouritePins();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        isActive = true;
        pinsPanel = new PinsPanel(myMap);
        menuPanel = new MenuPanel(myMap);
    }


    // TODO finds the place where the user clicked and decides what to do from there
    public void keyHandler() {

    }

    // TODO allows the user to navigate around map by dragging mouse cursor ** least priority
    public void moveAround() {

    }


   // TODO allow user to zoom in/out by hitting zoom in/out button ** least priority
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

    public void paintComponent(Graphics g) {
        Image img = mapBackground.getImage();
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
        g.drawImage(img, 0, 0, null);

    }


    // TODO import images for the gui -- did i do it right???? idk
    private void loadImages() {
        String sep = System.getProperty("file.separator");

        mapBackground = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "ubc-campus-map.png");

        waterFountainIcon = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "yellow.png");

        pinIcon = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "yellow.png");
    }

    // TODO represents the warning pop up message for the viewer when they do something thats not allowed
    // handles exceptions thrown from keyHandler mostly
    private void warningPopUp(Exception e) {

    }

    // MODIFIES: myMap
    // EFFECTS: saves the workroom to file
    private void saveState() {
        try {
            myMap.updateFavToList(favPins.getFavPins());
            myMap.updateAllToList(allPins.getAllPins());

            jsonWriter.open();
            jsonWriter.write(myMap);
            jsonWriter.close();
            System.out.println("Saved " + myMap.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadState() {
        try {
            myMap = jsonReader.read();
            favPins.addPins(myMap.getFavPins());
            allPins.addPins(myMap.getAllPins());
            System.out.println("Loaded " + myMap.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
