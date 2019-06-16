package Enum;

import javafx.scene.layout.Pane;

public enum EnumPane {
    LOGIN(1600, 800), HOMESCREEN(1600, 800), TITLESCREEN(1600, 800), GAMESCREEN(1600, 800),
    MAP(1200, 600, 200, 0), PLAYERLIST(200, 600, 0, 100), DICE(200, 100, 1400, 0);

    public Pane myPane = new Pane();

    EnumPane(int width, int height) {
        myPane.setMinSize(width, height);
    }

    EnumPane(int width, int height, int xLocation, int yLocation) {
        myPane.setMinSize(width, height);
        myPane.setTranslateX(xLocation);
        myPane.setTranslateY(yLocation);
    }
}
