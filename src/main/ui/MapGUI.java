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
    protected static BackgroundPanel background;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Map myMap;
    protected static HashMap<Point, Pin> pinPoints;

    private JMenuBar menuPanel;

    public MapGUI() {
        super("UBC fountains");
        setSize(INITIAL_SCREEN_WIDTH, INITIAL_SCREEN_HEIGHT);

        loadImages();
        Image img = mapBackground.getImage();
        background = new BackgroundPanel(
                img.getScaledInstance(INITIAL_SCREEN_WIDTH, INITIAL_SCREEN_HEIGHT, img.SCALE_SMOOTH),
                pinIcon.getImage(),
                waterFountainIcon.getImage());

        this.setContentPane(background);

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

        this.addMouseListener(this);

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
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateSearchPanel();
            }
        });

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

    private void generateSearchPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JCheckBox byTag = new JCheckBox("tag search");
        JCheckBox byLocation = new JCheckBox("location search");
        JTextField searchKey = new JTextField("keyword here", 16);

        panel.add(searchKey);
        panel.add(byTag);
        panel.add(byLocation);

        int option = JOptionPane.showConfirmDialog(
                container,
                panel,
                "Search",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE
        );

        handleSearchInput(option, byTag.isSelected(),byLocation.isSelected(), searchKey.getText());


    }

    private void handleSearchInput(int option, boolean byTagSelected, boolean byLocSelected, String key) {
        if (option == JOptionPane.OK_OPTION) {
            if (byTagSelected && byLocSelected) {
                JOptionPane.showMessageDialog(container, "You can't search by both keys!");
            } else if (byTagSelected) {
                searchTag(key);
            } else if (byLocSelected) {
                searchLocation(key);
            } else {
                JOptionPane.showMessageDialog(container, "You need to select a search scope!");
            }
        }
    }


    private void searchLocation(String key) {
        HashMap<Point, Pin> searchResult = new HashMap<>();
        for (Point point : pinPoints.keySet()) {
            Pin pin = pinPoints.get(point);

            if (pin.getLocation().equals(key)) {
                searchResult.put(point, pin);
            }

        }
        background.updateMapImage(searchResult);
    }

    private void searchTag(String key) {
        HashMap<Point, Pin> searchResult = new HashMap<>();
        for (Point point : pinPoints.keySet()) {
            Pin pin = pinPoints.get(point);

            if (pin.getTag().equals(key)) {
                searchResult.put(point, pin);
            }

        }
        background.updateMapImage(searchResult);
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
            PinMakerPopup pinMaker = new PinMakerPopup(point, this);
            this.add(pinMaker.getPinMakerScreen());
        }
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
