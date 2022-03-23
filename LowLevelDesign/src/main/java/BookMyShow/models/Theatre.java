package BookMyShow.models;

import java.util.ArrayList;
import java.util.List;

public class Theatre {
    String name;
    String id;
    List<Screen> screens;

    Theatre(String name, String id) {
        this.name = name;
        this.id = id;
        this.screens = new ArrayList<>();
    }

    public void addScreen(Screen screen) {
        screens.add(screen);
    }
}
