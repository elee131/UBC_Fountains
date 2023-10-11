package model;

import java.util.List;

public interface PinList {

    List<Pin> searchTag(String tag);

    List<Pin> searchLocation(String location);

    boolean addPin(Pin pin);

}
