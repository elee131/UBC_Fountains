package model;

import java.util.List;

public interface PinList {

    List<Pin> searchTag(String str);

    List<Pin> searchLocation();

    boolean addPin(Pin pin);

}
