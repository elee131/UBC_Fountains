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
import java.util.ArrayList;


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

    protected static java.util.List<Point> pointList;
    protected static BackgroundPanel background;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Map myMap;

    private JMenuBar menuPanel;

    //REQUIRES: each element of allPins and pointList must correspond to each other, thus they must be the same length
    public MapGUI() {
        super("UBC fountains");
        setSize(INITIAL_SCREEN_WIDTH, INITIAL_SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setVisible(true);
        setLayout(null);
        setLayout(new BorderLayout());
        this.addMouseListener(this);

        menuPanel = new JMenuBar();
        buildMenu(menuPanel);
        this.setJMenuBar(menuPanel);

        loadImages();
        Image img = mapBackground.getImage();
        background = new BackgroundPanel(
                img.getScaledInstance(INITIAL_SCREEN_WIDTH, INITIAL_SCREEN_HEIGHT, img.SCALE_SMOOTH),
                pinIcon.getImage(),
                waterFountainIcon.getImage());
        this.setContentPane(background);

        initializeFields();
        initializeSomePins();

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes some pins for the constructor for testing purposes
    private void initializeSomePins() {
        allPins.addPin(new UserPin("ICCS", "Water"));
        allPins.addPin(new WaterFountain("ICCS"));
        allPins.addPin(new UserPin("NEST", "food"));
        pointList.add(new Point(200,300));
        pointList.add(new Point(300, 400));
        pointList.add(new Point(600, 100));
        background.updatePinsAndPoints(allPins.getAllPins(), pointList);
    }

    // MODIFIES: this
    // EFFECTS: initialize all fields for the constructor
    private void initializeFields() {
        myMap = new Map("My Map");
        favPins = new FavouritePins();
        allPins = new AllPins();
        pointList = new ArrayList<>();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        container = new JFrame();
        container.setSize(300,300);

    }

    // MODIFIES: this
    // EFFECTS: builds the menu bar the user can use to navigate the program
    private void buildMenu(JMenuBar menuPanel) {
        JMenu main = new JMenu("Main Menu");
        menuPanel.add(main, BorderLayout.PAGE_START);

        JMenuItem search = getSearch();
        JMenuItem favourites = getFavourites();
        JMenuItem save = getSave();
        JMenuItem load = getLoad();

        main.add(search);
        main.add(favourites);
        main.add(save);
        main.add(load);
    }

    // EFFECTS: builds the search menuItem for the menuBar
    private JMenuItem getSearch() {
        JMenuItem search = new JMenuItem("Search");
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateSearchPanel();
            }
        });
        return search;
    }

    // EFFECTS: builds the Favourites menuItem for the menuBar
    private JMenuItem getFavourites() {
        JMenuItem favourites = new JMenuItem("Favourites");
        favourites.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayFavs();
            }
        });
        return favourites;
    }

    // EFFECTS: builds the load menuItem for the menuBar
    private JMenuItem getLoad() {
        JMenuItem load = new JMenuItem("Load");
        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadState();
            }

        });
        return load;
    }

    // EFFECTS: builds the save menuItem for the menuBar
    private JMenuItem getSave() {
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveState();
            }

        });
        return save;
    }

    // MODIFIES: background
    // EFFECTS: displays only the pins marked as favourites
    private void displayFavs() {
        // TODO -- need to refactor backgroundpanel first

    }

    // MODIFIES: this
    // EFFECTS: builds the pop up window for the users to use for searching by keywords
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

    // MODIFIES: this
    // EFFECTS: when given the search input, use the inputs to search the results the user is looking for
    //          display dialog when user does something not allowed and display dialog
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


    // MODIFIES: background
    // EFFECTS: search the map for the user input with location as its scope and display the results
    private void searchLocation(String key) {
        java.util.List<Pin> searchResult = allPins.searchLocation(key);
        java.util.List<Point> searchResultPoints = new ArrayList<>();

        for (Pin pin : searchResult) {
            int indexOfPoint = allPins.getAllPins().indexOf(pin);
            searchResultPoints.add(pointList.get(indexOfPoint));
        }

        background.updatePinsAndPoints(searchResult, searchResultPoints);
    }

    // MODIFIES: background
    // EFFECTS: search the map for the user input with tag as its scope and display the results
    private void searchTag(String key) {
        java.util.List<Pin> searchResult;
        searchResult = allPins.searchTag(key);
        java.util.List<Point> searchResultPoints = new ArrayList<>();

        for (Pin pin : searchResult) {
            int indexOfPoint = allPins.getAllPins().indexOf(pin);
            searchResultPoints.add(pointList.get(indexOfPoint));
        }

        background.updatePinsAndPoints(searchResult, searchResultPoints);
    }

    // MODIFIES: myMap
    // EFFECTS: saves the workroom to file
    private void saveState() {
        try {
            myMap.updateFavToList(favPins.getFavPins());
            myMap.updateAllToList(allPins.getAllPins());
            myMap.updatePointList(pointList);

            jsonWriter.open();
            jsonWriter.write(myMap);
            jsonWriter.close();

            JOptionPane.showMessageDialog(container, "Saved " + myMap.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(container, "Unable to write to file: " + JSON_STORE);

        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadState() {
        try {
            myMap = jsonReader.read();
            favPins.addPins(myMap.getFavPins());
            allPins.addPins(myMap.getAllPins());

            pointList.clear();
            pointList.addAll(myMap.getAllPoints());

            background.updatePinsAndPoints(allPins.getAllPins(), pointList);
            System.out.println(allPins.toString());

            JOptionPane.showMessageDialog(container, "Loaded " + myMap.getName() + " from " + JSON_STORE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(container, "Unable to read from file: " + JSON_STORE);

        }
    }


    // EFFECTS: checks if the user has clicked on a pin. return pin if the user has, null otherwise
    public Pin clickedPin(Point p) {
        Pin selected = null;
        double posX = p.getX();
        double posY = p.getY();

        for (Point point : pointList) {
            boolean closeToX = (Math.abs(posX - point.getX()) <= PIN_WIDTH_HEIGHT);
            boolean closeToY = (Math.abs(posY - point.getY()) <= PIN_WIDTH_HEIGHT);

            if (closeToX && closeToY) {
                int indexOfPin = pointList.indexOf(point);
                selected = allPins.getAllPins().get(indexOfPin);
            }
        }

        return selected; // stub
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
