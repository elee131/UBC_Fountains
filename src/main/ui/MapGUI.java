package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    private static ImageIcon mapBackground;

    protected static JFrame container;

    private ImageIcon waterFountainIcon;
    private ImageIcon pinIcon;

    protected static AllPins allPins;
    protected static FavouritePins favPins;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Map myMap;
    protected static HashMap<Point, Pin> pinPoints;

    private JMenuBar menuPanel;


    private PinMakerPopup popUp;



    // TODO
    public MapGUI() {
        super("UBC fountains");
        setSize(INITIAL_SCREEN_WIDTH, INITIAL_SCREEN_HEIGHT);

        loadImages();
        Image img = mapBackground.getImage();

        this.setContentPane(new BackgroundPanel(
                img.getScaledInstance(INITIAL_SCREEN_WIDTH, INITIAL_SCREEN_HEIGHT, img.SCALE_SMOOTH)));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setVisible(true);
        setLayout(null);

        initializeFields();
        menuPanel = new JMenuBar();
        buildMenu(menuPanel);
        setLayout(new BorderLayout());
        this.add(menuPanel, BorderLayout.NORTH);

        pinPoints.put(new Point(200, 300), new UserPin("ICCS", "Water"));
        pinPoints.put(new Point(1, 800), new WaterFountain("ICCS"));

        displayPin();
        this.addMouseListener(this);

//        JPanel panel = new PinsPanel();
//        this.add(panel, BorderLayout.CENTER);

//        popUp = new JOptionPane("popup window test");
//        popUp.setVisible(true);
//        this.add(popUp, BorderLayout.CENTER);

//        popUp = new PinEditPopup();
//        this.add(popUp);

        popUp = new PinMakerPopup();

        setVisible(true);

    }

    // EFFECTS: initialize all fields for the constructor
    private void initializeFields() {
        favPins = new FavouritePins();
        allPins = new AllPins();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        pinPoints = new HashMap();
        container = new JFrame();
        container.setSize(300,300);

    }

    // EFFECTS: builds the menu bar the user can use to navigate the program
    private void buildMenu(JMenuBar menuPanel) {
        JMenu main = new JMenu("Main Menu");
        menuPanel.add(main, BorderLayout.PAGE_START);

        JMenuItem search = new JMenuItem("Search");
//        search.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                generateSearchPanel();
//            }
//        });

        JMenuItem favourites = new JMenuItem("Favourites");
//        favourites.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                displayFavs();
//            }
//        });
//
        JMenuItem printAllPins = new JMenuItem("Print allPins");
        printAllPins.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (Pin pin : allPins.getAllPins()) {
                    System.out.println(pin.getTag() + pin.getLocation() + pin.getStatus());
                }
            }

        });

        main.add(search);
        main.add(favourites);
        main.add(printAllPins);
        main.setVisible(true);
        menuPanel.setVisible(true);
    }

    private void displayFavs() {

        java.util.List<Pin> favs = favPins.getFavPins();


        for (Point point : pinPoints.keySet()) {
            JLabel pin;

            if (!favs.contains(pinPoints.get(point))) {
                continue;
            }

            if (pinPoints.get(point).getTag().equals("Water Fountain")) {
                pin = new JLabel(waterFountainIcon);
            } else {
                pin = new JLabel(pinIcon);
            }

            this.add(pin);
            pin.setBounds((int) point.getX(), (int) point.getY(), 50, 50);

        }

    }

    private void displayAll() {

        java.util.List<Pin> all = allPins.getAllPins();

        for (Point point : pinPoints.keySet()) {
            JLabel pin;

            if (pinPoints.get(point).getTag().equals("Water Fountain")) {
                pin = new JLabel(waterFountainIcon);
            } else {
                pin = new JLabel(pinIcon);
            }

            this.add(pin);
            pin.setBounds((int) point.getX(), (int) point.getY(), 50, 50);

        }
    }



    private void generateSearchPanel() {

//        JTextField tag;
//        JTextField location;
//
//        Object[] textFields = {
//                "Tag: ", tag,
//                "Location: ", location,
//
//        };
//
//        JOptionPane search = new JOptionPane();
//        JFrame container = new JFrame();
//        container.setSize(300, 300);
//        int option =
//        JOptionPane.showConfirmDialog(container, textFields, "Create new Pin", JOptionPane.OK_CANCEL_OPTION);
//
//        if (option == 0) {
//            createWaterFountain();
//        } else if (option == 1) {
//            createUserPin();
//        }

    }

    private void displaySearchResult() {

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

        for (Point point : pinPoints.keySet()) {
            JLabel pin;
            if (pinPoints.get(point).getTag().equals("Water Fountain")) {
                pin = new JLabel(waterFountainIcon);
            } else {
                pin = new JLabel(pinIcon);
            }

            this.add(pin);
            pin.setBounds((int) point.getX(), (int) point.getY(), 50, 50);

        }

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
            Pin chosen = pinPoints.get(point);
            if (chosen.getTag().equals("Water Fountain")) {
                JOptionPane editor = new PinEditPopup((WaterFountain) chosen);
                this.add(editor);
            } else {
                JOptionPane editor = new PinEditPopup((UserPin) chosen);
                this.add(editor);
            }
        } else {
            JOptionPane pinMaker = new PinMakerPopup();
            this.add(pinMaker);
        }
    }

    public void addPin(Pin pin) {
        JLabel waterFountain = new JLabel(waterFountainIcon);
        add(waterFountain);
    }



    // EFFECTS: loads images from the images folder
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
