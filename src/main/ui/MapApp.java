package ui;

import model.*;

import java.util.List;
import java.util.Scanner;

// TODO
// write class level comments

public class MapApp {

    private Scanner input;
    private Scanner secondInput;
    private String location;
    private String tag;
    private String direction;
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
        System.out.println("Welcome to UBC fountains!");
        System.out.println("What would you like to do? Press the number key next to the action you would"
                + " like to perform.");
        System.out.println("press q to quit at any time.");

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
        String command = null;

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

    // TODO
    // EFFECTS:
    private void editPinCommand() {
        Pin selected = selectPin();
        String command = null;
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

    private void editPin(String command, Pin pin) {
        String newInfo = null;

        if (command.equals("l")) {
            System.out.println("enter the new location of this pin (nearest UBC building code): ");
            newInfo = input.next();
            pin.setLocation(newInfo);

        } else if (command.equals("s")) {
            System.out.println("enter the new status of this pin (One of Broken, Unavailable, Working, Available): ");
            newInfo = input.next();
            pin.setLocation(newInfo);

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

    // TODO
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

    private void whichListToRemoveFrom() {
        String command = null;

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

    private void whichListToSearch() {
        String command = null;

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

    private void tagSearchInAll() {
        List<Pin> searchResult;
        String userInput;
        System.out.println("Enter the location  you wish to search for: ");
        userInput = input.next();
        searchResult = allPins.searchLocation(userInput);
        for (Pin pin : searchResult) {
            System.out.println(pin.getLocation() + "," + pin.getTag() + ","
                    + pin.getStatus());
        }
    }

    private void locationSearchInAll() {
        List<Pin> searchResult;
        String userInput;
        System.out.println("Enter the location  you wish to search for: ");
        userInput = input.next();
        searchResult = allPins.searchLocation(userInput);
        for (Pin pin : searchResult) {
            System.out.println(pin.getLocation() + "," + pin.getTag() + ","
                    + pin.getStatus());
        }
    }

    private void idSearchInAll() {
        Pin idSearchResult;
        String userInput;
        System.out.println("Enter the id of the pin you wish to search for: ");
        userInput = input.next();
        idSearchResult = allPins.searchID(userInput);
        if (!(null == idSearchResult)) {
            System.out.println(idSearchResult.getLocation() + "," + idSearchResult.getTag() + ","
                    + idSearchResult.getStatus());
        } else {
            System.out.println("no such result :(");
        }
    }


    private void tagSearchInFav() {
        List<Pin> searchResult;
        String userInput;
        System.out.println("Enter the location you wish to search for: ");
        userInput = input.next();
        searchResult = favPins.searchLocation(userInput);
        for (Pin pin : searchResult) {
            System.out.println(pin.getLocation() + "," + pin.getTag() + ","
                    + pin.getStatus());
        }
    }

    private void locationSearchInFav() {
        List<Pin> searchResult;
        String userInput;
        System.out.println("Enter the location you wish to search for: ");
        userInput = input.next();
        searchResult = favPins.searchLocation(userInput);
        for (Pin pin : searchResult) {
            System.out.println(pin.getLocation() + "," + pin.getTag() + ","
                    + pin.getStatus());
        }
    }

    private void idSearchInFav() {
        Pin idSearchResult;
        String userInput;
        System.out.println("Enter the id of the pin you wish to search for: ");
        userInput = input.next();
        idSearchResult = favPins.searchID(userInput);
        if (!(null == idSearchResult)) {
            System.out.println(idSearchResult.getLocation() + "," + idSearchResult.getTag() + ","
                    + idSearchResult.getStatus());
        } else {
            System.out.println("no such result :(");
        }
    }

    // EFFECTS: processes user command
    private void searchAllCommand() {
        String command = null;

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
        String command = null;

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
    // MODIFIES: WaterFountain, AllPins
    // EFFECTS: makes new fountain with given location from input and adds it to list of all pins
    public void makeWaterFountain() {
        String yesNo = null;
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
    // MODIFIES: UserPin, AllPins
    // EFFECTS: makes new UserPin with given location and tag from input, adds it to list of all pins
    public void makeUserPin() {
        String yesNo = null;
        input = new Scanner(System.in);
        secondInput = new Scanner(System.in);

        System.out.println("Enter the building code where the feature is closest to: ");
        location = input.nextLine();

        System.out.println("Enter the 'tag' you wish to use in order to describe this pin.");
        tag = secondInput.nextLine();

        Pin newUserPin = new UserPin(location, tag);
        System.out.println("New pin made!");
        maybeAddDirection(newUserPin);
        maybeAddToFav(newUserPin);

        System.out.println("All done!");
    }

    // REQUIRES: favPins is not empty
    // MODIFIES: favPins
    // EFFECTS: removes pins that match the user's wishes
    public void removeFromFavCommand() {
        String command = null;
        String id = null;
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
            System.out.println("poof! it's gone :)");
        } else {
            System.out.println("the pin(s) you were looking to remove were not there");
        }
    }

    // TODO
    public void removeFromAllCommand() {
        String command = null;
        String id = null;
        Pin removedPin;

        System.out.println("How do you want to edit your list of pins?");
        System.out.println("[1] Remove all broken/unavailable pins");
        System.out.println("[2] Remove a pin by ID");

        command = input.next();

        if (command.equals("1")) {
            allPins.removeAllUnavailable();
        } else if (command.equals("2")) {
            System.out.println("Enter the ID of the pin you wish to remove: ");
            id = secondInput.next();
            removedPin = allPins.searchID(id);
            allPins.removePin(removedPin);
        }

    }

    // TODO
    // MODIFIES: WaterFountain or UserPin
    // EFFECTS: select a pin based on user input then edit the parameter of the user's choosing of the pin
    public Pin selectPin() {
        String id = null;
        Pin selected;

        System.out.println("Enter the ID of the pin you wish to select: ");
        id = input.next();

        selected = allPins.searchID(id);

        return selected;
    }

    // EFFECTS: print the favourite pins, listing its location, tag, and status
    public void viewFavourites() {
        System.out.println("Pins are shown in the format 'location, tag, status'");
        for (Pin pin : favPins.getFavPins()) {
            System.out.println(pin.getLocation() + " ," + pin.getTag() + " ," + pin.getStatus());
        }

    }


    // EFFECTS: print all pins present in the map, listing its location, tag, and status
    public void viewAll() {
        System.out.println("Pins are shown in the format 'location, tag, status' with pin ID printed below");
        for (Pin pin : allPins.getAllPins()) {
            System.out.println(pin.getLocation() + " ," + pin.getTag() + " ," + pin.getStatus());
            System.out.println(pin.getId());
        }
    }

    // TODO -- add a lil bit more pins to start with
    // EFFECTS: adds bunch of water fountains to allPins to get users started
    //
    public void init() {
        Pin icicsFountain = new WaterFountain("ICCS");
        Pin mcldFountain = new WaterFountain("MCLD");
        Pin lifeFountain = new WaterFountain("LIFE");

        allPins.addPin(icicsFountain);
        allPins.addPin(mcldFountain);
        allPins.addPin(lifeFountain);

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
