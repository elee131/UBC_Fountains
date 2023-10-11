package model;

public class UserPin implements Pin {
    private String tag;
    private String status;
    private String location;


    public UserPin(String location, String tag) {

        this.location = location;
        this.tag = tag;
        this.status = "Working";

    }

    @Override
    public void setStatus(String status) {

    }

    @Override
    public void addToFavourite() {

    }
}
