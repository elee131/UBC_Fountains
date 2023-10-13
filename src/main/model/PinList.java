package model;

import java.util.List;

public interface PinList {

    List<Pin> searchTag(String tag);

    List<Pin> searchLocation(String location);

    Pin searchID(String id);

    boolean addPin(Pin pin);


}
