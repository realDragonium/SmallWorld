package Enum;

import javafx.scene.paint.Color;

public enum AreaColor {
    forest(Color.DARKGREEN), water(Color.AQUA),hill(Color.LIGHTGREEN), mountain(Color.GRAY), swamp(Color.BROWN), farm(Color.YELLOW.darker());
    private Color color;
    AreaColor(Color color){
        this.color = color;
    }

    public Color getColor(){
        return color;
    }
}
