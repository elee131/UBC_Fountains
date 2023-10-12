package ui;

import model.*;

import java.util.Scanner;

public class MapApp {

    Scanner scanner1;
    Scanner scanner2;
    String location;
    String tag;
    String direction;
    String yesNoInput;
    AllPins allPins;
    FavouritePins favPins;

    public MapApp() {
        allPins = new AllPins();
        favPins = new FavouritePins();

    }

    // TODO
    // EFFECTS: start the MapApp by giving the user a list of options to choose from or quit


    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void startUp() {
        System.out.println("Welcome to UBC fountains!");
        System.out.println("What would you like to do? Press the number key next to the action you would"
                + " like to perform.");
        System.out.println("[1] Make new water fountain pin");
        System.out.println("[2] Make new pin");
        System.out.println("[3] Edit existing pin");
        System.out.println("[4] Search pins by Tags");
        System.out.println("[5] Search pins by Location");
        System.out.println("[6] View Favourite pins");
        System.out.println("[7] View all pins");

        scanner1 = new Scanner(System.in);
        int choice = scanner1.nextInt();

        if (choice == 1) {
            makeWaterFountain();
        } else if (choice == 2) {
            makeUserPin();
        } else if (choice == 3) {
            editPin();
        } else if (choice == 4) {
            searchByTagsAll();
        } else if (choice == 5) {
            searchByLocationAll();
        } else if (choice == 6) {
            viewFavourites();
        } else if (choice == 7) {
            viewAll();
        }
    }

    // TODO
    // REQUIRES: location must be valid building code within UBC
    // MODIFIES: WaterFountain, AllPins
    // EFFECTS: makes new fountain with given location from input and adds it to list of all pins
    public void makeWaterFountain() {
        scanner1 = new Scanner(System.in);
        System.out.println("Enter the building code where the fountain is in: ");

        scanner1 = new Scanner(System.in);
        location = scanner1.nextLine();

        Pin newWaterFountain = new WaterFountain(location);
        if (allPins.addPin(newWaterFountain)) {
            System.out.println("New Fountain pinned!");
            System.out.println("Would you like to add directions? Enter Y/N");

            scanner1 = new Scanner(System.in);
            yesNoInput = scanner1.nextLine();

            if (yesNoInput == "Y") {
                System.out.println("Enter a brief direction");
                scanner2 = new Scanner(System.in);
                direction = scanner2.nextLine();
                newWaterFountain.setDirection(direction);
            }
            // return to start

        } else {
            System.out.println("That fountain already exists!");
        }
    }

    // TODO
    // REQUIRES: location must be valid building code within UBC
    // MODIFIES: UserPin, AllPins
    // EFFECTS: makes new UserPin with given location and tag from input, adds it to list of all pins
    public void makeUserPin() {
        scanner1 = new Scanner(System.in);
        System.out.println("Enter the building code where the feature is closest to: ");

        scanner1 = new Scanner(System.in);
        location = scanner1.nextLine();

        scanner2 = new Scanner(System.in);
        System.out.println("Enter the 'tag' you wish to use in order to describe this pin.");
        tag = scanner2.nextLine();

        Pin newUserPin = new UserPin(location, tag);
        if (allPins.addPin(newUserPin)) {
            System.out.println("New pin made!");
            System.out.println("Would you like to add directions? Enter Y/N");

            scanner1 = new Scanner(System.in);
            yesNoInput = scanner1.nextLine();

            if (yesNoInput == "Y") {
                scanner2 = new Scanner(System.in);
                direction = scanner2.nextLine();
                newUserPin.setDirection(direction);
            }
            // return to start

        } else {
            System.out.println("That pin already exists!");
        }

    }

    // TODO
    // REQUIRES: allPins is not empty
    // MODIFIES: allPins
    // EFFECTS: remove pins based on parameter of user choice then uses user input to
    // get rid of the pin

    public void remove() {


    }

    // TODO
    // MODIFIES: WaterFountain or UserPin
    // EFFECTS: select a pin based on user input then edit the parameter of the user's choosing of the pin
    public void editPin() {

    }

    // TODO
    // EFFECTS: print the favourite pins
    public void viewFavourites() {
        System.out.println("Pins are shown in the format 'location, tag, status'");
        for (Pin pin : favPins.getFavPins()) {
            System.out.println(pin.getLocation() + "," + pin.getTag() + "," + pin.getStatus());
        }

    }

    // TODO
    // EFFECTS: print all pins present in the map
    public void viewAll() {
        System.out.println("Pins are shown in the format 'location, tag, status'");
        for (Pin pin : allPins.getAllPins()) {
            System.out.println(pin.getLocation() + "," + pin.getTag() + "," + pin.getStatus());
        }
    }

    // TODO
    // EFFECTS: search pins by their tags from allPins
    public void searchByTagsAll() {
        System.out.println("Enter the tag you are searching for: ");


    }

    // TODO
    // EFFECTS: search pins by their location from allPins
    public void searchByLocationAll() {

    }




}
