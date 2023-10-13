package ui;

import model.*;

import java.util.List;
import java.util.Scanner;

// an interactive map application
// allPins represent every pin present in the application
// favPins represent pins the user adds to their personal collection
public class MapApp {

    private static final String NO_SEARCH_RESULTS = "No search results found :(";
    private static final String NOTHING_TO_REMOVE = "Nothing here to remove :)";
    private static final String SUCCESSFUL_REMOVAL = "Poof! It's gone :)";
    public static final String NOTHING_IN_LIST = "There are no pins here :/";

    public static final String PRINTED_PIN_FORMAT = "Pins are shown in the format 'location, tag, status' with "
            + "pin ID and direction printed below";
    private Scanner input;
    private Scanner secondInput;
    private String location;
    private AllPins allPins;
    private FavouritePins favPins;

    // EFFECTS: constructs an instance of MapApp with allPins list that encompasses all Pins,
    // and favPins that stores the user's favourite pins. runs the teller application
    public MapApp() {
        allPins = new AllPins();
        favPins = new FavouritePins();

        runMap();
    }


    // EFFECTS: text menu to let users navigate between options
    private static void displayMenu() {
        System.out.println("\nWelcome to UBC fountains!");
        System.out.println("What would you like to do? Press the number key next to the action you would"
                + " like to perform.");
        System.out.println("press q to quit.");

        System.out.println("[1] Make new water fountain");
        System.out.println("[2] Make new general pin!");
        System.out.println("[3] Add to favourite!");
        System.out.println("[4] Search pins");
        System.out.println("[5] Edit pins");
        System.out.println("[6] Remove pins");
        System.out.println("[7] View favourites");
        System.out.println("[8] View all pins");

    }

    // MODIFIES: this
    // EFFECTS: process user input
    private void runMap() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye! Stay hydrated!");
    }

    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            makeWaterFountain();
        } else if (command.equals("2")) {
            makeUserPin();
        } else if (command.equals("3")) {
            addToFavourites();
        } else if (command.equals("4")) {
            whichListToSearch();
        } else if (command.equals("5")) {
            editPinCommand();
        } else if (command.equals("6")) {
            whichListToRemoveFrom();
        } else if (command.equals("7")) {
            viewFavourites();
        } else if (command.equals("8")) {
            viewAll();
        }
    }


    // EFFECTS: process user input and edit the selected pin accordingly
    private void editPinCommand() {
        Pin selected = selectPin();
        String command;
        if (!(selected == null)) {
            System.out.println("What about the Pin would you like to change?");
            System.out.println("[l] location");
            System.out.println("[s] status");
            System.out.println("[t] tag");
            System.out.println("[d] directions");

            command = input.next();
            editPin(command, selected);
        } else {
            System.out.println("That pin does not exist :(");
        }
    }

    // REQUIRES: command must be one of "l", "s", "t", or "d"
    // MODIFIES: pin
    // EFFECTS: edits a pin's field based on the commands passed down from editPinCommand
    private void editPin(String command, Pin pin) {
        String newInfo;

        if (command.equals("l")) {
            System.out.println("enter the new location of this pin (nearest UBC building code): ");
            newInfo = input.next();
            pin.setLocation(newInfo);

        } else if (command.equals("s")) {
            System.out.println("enter the new status of this pin (One of Broken, Unavailable, Working, Available): ");
            newInfo = input.next();
            pin.setStatus(newInfo);

        } else if (command.equals("t")) {
            System.out.println("enter the new tag of this pin: ");
            newInfo = input.next();
            pin.setTag(newInfo);

        } else if (command.equals("d")) {
            System.out.println("enter the new direction of this pin: ");
            newInfo = input.next();
            pin.setDirection(newInfo);

        }
    }


    // EFFECTS: adds the user-selected pin to favourites, if it is not already in the list and if it exists
    private void addToFavourites() {
        Pin selected = selectPin();
        boolean success = false;
        if (!(selected == null)) {
            success = favPins.addPin(selected);
        }

        if (success) {
            System.out.println("Yay! Added to favourites.");
        } else {
            System.out.println("that already exists in favourite or the pin does not exist :(");
        }
    }

    // EFFECTS: processes user input and directs them to the correct remove method
    private void whichListToRemoveFrom() {
        String command;

        System.out.println("Which list do you want to remove from?");
        System.out.println("[a] List of all pins");
        System.out.println("[f] Favourite pins");

        command = input.next();

        if (command.equals("a")) {
            removeFromAllCommand();
        } else if (command.equals("f")) {
            removeFromFavCommand();
        }
    }

    // EFFECTS: processes user input and directs them to the correct search method
    private void whichListToSearch() {
        String command;

        System.out.println("Which list do you want to search through?");
        System.out.println("[a] List of all pins");
        System.out.println("[f] Favourite pins");

        command = input.next();

        if (command.equals("a")) {
            searchAllCommand();
        } else if (command.equals("f")) {
            searchFavCommand();
        }

    }

    // EFFECTS: print pins' information in allPins with the matching tag. Print a message for the user
    // if no match is found
    private void tagSearchInAll() {
        List<Pin> searchResult;
        String userInput;
        System.out.println("Enter the tag you wish to search for: ");
        userInput = input.next();
        searchResult = allPins.searchTag(userInput);

        System.out.println(PRINTED_PIN_FORMAT);
        for (Pin pin : searchResult) {
            System.out.println(pin.getLocation() + ", " + pin.getTag() + ", " + pin.getStatus());
            System.out.println(pin.getId());
            System.out.println(pin.getDirections());
        }
        if (searchResult.isEmpty()) {
            System.out.println(NO_SEARCH_RESULTS);
        }
    }

    // EFFECTS: print pins' information in allPins with the matching location. Print a message for the user
    // if no match is found
    private void locationSearchInAll() {
        List<Pin> searchResult;
        String userInput;
        System.out.println("Enter the location you wish to search for: ");
        userInput = input.next();
        searchResult = allPins.searchLocation(userInput);

        System.out.println(PRINTED_PIN_FORMAT);

        for (Pin pin : searchResult) {
            System.out.println(pin.getLocation() + ", " + pin.getTag() + ", " + pin.getStatus());
            System.out.println(pin.getId());
            System.out.println(pin.getDirections());
        }
        if (searchResult.isEmpty()) {
            System.out.println(NO_SEARCH_RESULTS);
        }
    }

    // EFFECTS: print pins' information in allPins with the matching id. Print a message for the user
    // if no match is found
    private void idSearchInAll() {
        Pin idSearchResult;
        String userInput;
        System.out.println("Enter the id of the pin you wish to search for: ");
        userInput = input.next();
        idSearchResult = allPins.searchID(userInput);

        if (!(null == idSearchResult)) {
            System.out.println(PRINTED_PIN_FORMAT);
            System.out.println(idSearchResult.getLocation() + ", " + idSearchResult.getTag() + ", "
                    + idSearchResult.getStatus());
            System.out.println(idSearchResult.getId());
            System.out.println(idSearchResult.getDirections());
        } else {
            System.out.println(NO_SEARCH_RESULTS);
        }
    }

    // EFFECTS: print pins' information in favPins with the matching tag. Print a message for the user
    // if no match is found
    private void tagSearchInFav() {
        List<Pin> searchResult;
        String userInput;
        System.out.println("Enter the tag you wish to search for: ");
        userInput = input.next();
        searchResult = favPins.searchTag(userInput);

        System.out.println(PRINTED_PIN_FORMAT);

        for (Pin pin : searchResult) {
            System.out.println(pin.getLocation() + ", " + pin.getTag() + ", " + pin.getStatus());
            System.out.println(pin.getId());
            System.out.println(pin.getDirections());
        }
        if (searchResult.isEmpty()) {
            System.out.println(NO_SEARCH_RESULTS);
        }
    }

    // EFFECTS: print pins' information in favPins with the matching location. Print a message for the user
    // if no match is found
    private void locationSearchInFav() {
        List<Pin> searchResult;
        String userInput;
        System.out.println("Enter the location you wish to search for: ");
        userInput = input.next();
        searchResult = favPins.searchLocation(userInput);

        System.out.println(PRINTED_PIN_FORMAT);

        for (Pin pin : searchResult) {
            System.out.println(pin.getLocation() + ", " + pin.getTag() + ", " + pin.getStatus());
            System.out.println(pin.getId());
            System.out.println(pin.getDirections());
        }
        if (searchResult.isEmpty()) {
            System.out.println(NO_SEARCH_RESULTS);
        }
    }

    // EFFECTS: print the pin's information in favPins with the matching id. Print a message for the user
    // if no match is found
    private void idSearchInFav() {
        Pin idSearchResult;
        String userInput;
        System.out.println("Enter the id of the pin you wish to search for: ");
        userInput = input.next();
        idSearchResult = favPins.searchID(userInput);

        if (!(null == idSearchResult)) {
            System.out.println(PRINTED_PIN_FORMAT);
            System.out.println(idSearchResult.getLocation() + ", " + idSearchResult.getTag() + ", "
                    + idSearchResult.getStatus());
            System.out.println(idSearchResult.getId());
            System.out.println(idSearchResult.getDirections());
        } else {
            System.out.println(NO_SEARCH_RESULTS);
        }
    }

    // EFFECTS: processes user command
    private void searchAllCommand() {
        String command;

        System.out.println("Which parameter would you like to search by?");
        System.out.println("[l] Location");
        System.out.println("[t] Tag");
        System.out.println("[i] Id");

        command = input.next();

        if (command.equals("l")) {
            locationSearchInAll();
        } else if (command.equals("t")) {
            tagSearchInAll();
        }  else if (command.equals("i")) {
            idSearchInAll();
        }
    }

    // EFFECTS: processes user command
    private void searchFavCommand() {
        String command;

        System.out.println("Which parameter would you like to search by?");
        System.out.println("[l] Location");
        System.out.println("[t] Tag");
        System.out.println("[i] Id");

        command = input.next();

        if (command.equals("l")) {
            locationSearchInFav();
        } else if (command.equals("t")) {
            tagSearchInFav();
        }  else if (command.equals("i")) {
            idSearchInFav();
        }
    }


    // REQUIRES: location must be valid building code within UBC
    // MODIFIES: this
    // EFFECTS: makes new fountain with given location from input and adds it to list of all pins
    public void makeWaterFountain() {
        input = new Scanner(System.in);
        secondInput = new Scanner(System.in);

        System.out.println("Enter the building code where the fountain is in: ");
        location = input.nextLine();

        Pin newWaterFountain = new WaterFountain(location);
        allPins.addPin(newWaterFountain);
        System.out.println("New Fountain pinned!");
        
        maybeAddDirection(newWaterFountain);
        maybeAddToFav(newWaterFountain);

        System.out.println("All done!");
    }

    // REQUIRES: location must be valid building code within UBC
    // MODIFIES: this
    // EFFECTS: makes new UserPin with given location and tag from input, adds it to list of all pins
    public void makeUserPin() {
        String tag;
        input = new Scanner(System.in);
        secondInput = new Scanner(System.in);

        System.out.println("Enter the building code where the feature is closest to: ");
        location = input.nextLine();

        System.out.println("Enter the 'tag' you wish to use in order to describe this pin.");
        tag = secondInput.nextLine();

        Pin newUserPin = new UserPin(location, tag);
        allPins.addPin(newUserPin);
        System.out.println("New pin made!");
        maybeAddDirection(newUserPin);
        maybeAddToFav(newUserPin);

        System.out.println("All done!");
    }

    // REQUIRES: favPins is not empty
    // MODIFIES: this
    // EFFECTS: removes pins that match the user's wishes
    public void removeFromFavCommand() {
        String command;
        String id;
        Pin removedPin;
        boolean success = false;

        System.out.println("How do you want to edit your favourites?");
        System.out.println("[1] Remove all broken/unavailable pins");
        System.out.println("[2] Remove a pin by ID");

        command = input.next();

        if (command.equals("1")) {
            success = favPins.removeAllUnavailable();

        } else if (command.equals("2")) {
            System.out.println("Enter the ID of the pin you wish to remove: ");
            id = input.next();
            removedPin = favPins.searchID(id);
            success = favPins.removePin(removedPin);
        }

        if (success) {
            System.out.println(SUCCESSFUL_REMOVAL);
        } else {
            System.out.println(NOTHING_TO_REMOVE);
        }
    }

    // REQUIRES: allPins is not empty
    // MODIFIES: this
    // EFFECTS: processes user input and lead them to the appropriate removal method
    public void removeFromAllCommand() {
        boolean success = false;
        String command;
        String id;
        Pin removedPin;

        System.out.println("How do you want to edit your list of pins?");
        System.out.println("[1] Remove all broken/unavailable pins");
        System.out.println("[2] Remove a pin by ID");

        command = input.next();

        if (command.equals("1")) {
            success = allPins.removeAllUnavailable();
            favPins.removeAllUnavailable();
        } else if (command.equals("2")) {
            System.out.println("Enter the ID of the pin you wish to remove: ");
            id = secondInput.next();
            removedPin = allPins.searchID(id);
            success = allPins.removePin(removedPin);
            favPins.removePin(removedPin);
        }

        if (success) {
            System.out.println(SUCCESSFUL_REMOVAL);
        } else {
            System.out.println(NOTHING_TO_REMOVE);
        }

    }


    // EFFECTS: return a pin with the ID the user provides, if no such pin exists, return null
    public Pin selectPin() {
        String id;
        Pin selected;

        System.out.println("Enter the ID of the pin you wish to select: ");
        id = input.next();

        selected = allPins.searchID(id);

        return selected;
    }

    // EFFECTS: print favourite pins, listing its location, tag, status, ID, and direction
    public void viewFavourites() {
        List<Pin> pinList = favPins.getFavPins();
        System.out.println(PRINTED_PIN_FORMAT);

        for (Pin pin : pinList) {
            System.out.println(pin.getLocation() + ", " + pin.getTag() + ", " + pin.getStatus());
            System.out.println(pin.getId());
            System.out.println(pin.getDirections());
        }

        if (pinList.isEmpty()) {
            System.out.println(NOTHING_IN_LIST);
        }
    }


    // EFFECTS: print all pins present in the map, listing its location, tag, status, ID, and direction
    public void viewAll() {
        List<Pin> pinList = allPins.getAllPins();

        System.out.println(PRINTED_PIN_FORMAT);
        for (Pin pin : allPins.getAllPins()) {
            System.out.println(pin.getLocation() + ", " + pin.getTag() + ", " + pin.getStatus());
            System.out.println(pin.getId());
            System.out.println(pin.getDirections());
        }
        if (pinList.isEmpty()) {
            System.out.println(NOTHING_IN_LIST);
        }
    }


    // MODIFIES: this
    // EFFECTS: adds a bunch of water fountains and some userPins to allPins to get users started
    public void init() {
        Pin icicsFountain = new WaterFountain("ICCS");
        Pin mcldFountain = new WaterFountain("MCLD");
        Pin lifeFountain = new WaterFountain("LIFE");
        Pin microwave = new UserPin("NEST", "Microwave");
        Pin chemFountain = new WaterFountain("CHEM");

        icicsFountain.setDirection("X wing, second floor by the men's bathroom");
        lifeFountain.setDirection("Second floor, by the men's bathroom. has water bottle filler.");

        microwave.setStatus("Broken");
        chemFountain.setStatus("Broken");

        allPins.addPin(icicsFountain);
        allPins.addPin(mcldFountain);
        allPins.addPin(lifeFountain);
        allPins.addPin(microwave);
        allPins.addPin(chemFountain);

        favPins.addPin(mcldFountain);
        favPins.addPin(microwave);

        input = new Scanner(System.in);
        input.useDelimiter("\n");

        secondInput = new Scanner(System.in);
        secondInput.useDelimiter("\n");
    }

    // EFFECTS: add the newly created pin to favourites if the user commands it
    // otherwise do nothing
    private void maybeAddToFav(Pin pin) {
        String yesNo;
        System.out.println("would you like to add this pin to favourites? Enter y if yes. Hit any other button if no");

        input = new Scanner(System.in);
        yesNo = input.nextLine();
        yesNo = yesNo.toLowerCase();

        if (yesNo.equals("y")) {
            favPins.addPin(pin);
        }
    }

    // EFFECTS: add directions to newly created pin if user commands it
    // otherwise do nothing
    private void maybeAddDirection(Pin pin) {
        String direction;
        String yesNo;
        System.out.println("Would you like to add directions? Enter y if yes. Hit any other button if no");

        yesNo = input.nextLine();
        yesNo = yesNo.toLowerCase();

        if (yesNo.equals("y")) {
            System.out.println("Enter a brief direction");
            secondInput = new Scanner(System.in);
            direction = secondInput.nextLine();
            pin.setDirection(direction);
        }
    }


}
