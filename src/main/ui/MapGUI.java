package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

// TODO - bring in every functionality from mapApp into MapGUI
// represents the main screen of the application with a map that displays pins
public class MapGUI extends JFrame implements MouseListener {

    private static final int INITIAL_SCREEN_WIDTH = 1500;
    public static final int INITIAL_SCREEN_HEIGHT = 1000;
    public static final double PIN_WIDTH_HEIGHT = 50;
    private static final String JSON_STORE = "./data/myMap.json";
    private ImageIcon mapBackground;
    private ImageIcon waterFountainIcon;
    private ImageIcon pinIcon;

    protected static AllPins allPins;
    protected static FavouritePins favPins;

    private Image bg;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Map myMap;
    private HashMap<Point, Pin> pinPoints;

    private JMenuBar menuPanel;
    private JPanel bgPanel;
    private PinsPanel panel;



    // TODO
    // all ripped from spaceinvaders
    public MapGUI() {
        super("UBC fountains");
        setSize(INITIAL_SCREEN_WIDTH, INITIAL_SCREEN_HEIGHT);

        loadImages();
//        bgPanel = new JPanel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                Image img = mapBackground.getImage();
//                img.getScaledInstance(INITIAL_SCREEN_WIDTH, INITIAL_SCREEN_HEIGHT, img.SCALE_SMOOTH);
//
//                setLayout(null);
//                g.drawImage(img, 0, 0,INITIAL_SCREEN_WIDTH, INITIAL_SCREEN_HEIGHT, null);
//            }
//        };

        Image img = mapBackground.getImage();


        // BufferedImage myImage = ImageIO.read(img);
        this.setContentPane(new BackgroundPanel(
                img.getScaledInstance(INITIAL_SCREEN_WIDTH, INITIAL_SCREEN_HEIGHT, img.SCALE_SMOOTH)));

        //this.add(bgPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setVisible(true);

        initializeFields();
        menuPanel = new JMenuBar();
        buildMenu(menuPanel);
        setLayout(new BorderLayout());
        this.add(menuPanel, BorderLayout.NORTH);

//        JPanel panel = new PinsPanel();
//        this.add(panel, BorderLayout.CENTER);

    }

    private void initializeFields() {
        favPins = new FavouritePins();
        allPins = new AllPins();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        pinPoints = new HashMap();

    }

    private void buildMenu(JMenuBar menuPanel) {
        JMenu main = new JMenu("Main Menu");
        menuPanel.add(main, BorderLayout.PAGE_START);
        JMenuItem subMenu = new JMenuItem("Search");
        subMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PinsPanel();
            }
        });
        main.add(subMenu);
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
//     might not do it tbh
//    public void helpMenu() {
//
//    }

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

    // search through the hashmap
    // EFFECTS: checks if the user has clicked on a pin. return pin if the user has, null otherwise
    public Pin clickedPin(Point p) {
        Pin selected = null;
        double posX = p.getX();
        double posY = p.getY();

        Collection<Point> points = pinPoints.keySet();

        for (Point point: points) {
            double dx = Math.abs(posX - point.getX());
            double dy = Math.abs(posY - point.getY());

            if ((dx <= PIN_WIDTH_HEIGHT) && (dy <= PIN_WIDTH_HEIGHT)) {
                selected = pinPoints.get(point);
            }
        }

        return selected; // stub
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        Point point = e.getPoint();
        Pin selected = clickedPin(point);
        if (selected != null) {
            selected.getStatus(); // -- stub
        } else {
            PinsPanel panel = new PinsPanel();
            if (panel.constructed()) {
                pinPoints.put(point, panel.getPin());
                addPin(panel.getPin());
            }

        }
    }

    public void addPin(Pin pin) {
        JLabel waterFountain = new JLabel(waterFountainIcon);
        add(waterFountain);
    }




    private void loadImages() {
        String sep = System.getProperty("file.separator");

        mapBackground = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "ubc-campus-map.png");

        waterFountainIcon = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "WaterFountain.png");

        pinIcon = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "UserPin.png");
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
