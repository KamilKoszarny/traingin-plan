package skills;

import javafx.scene.paint.Color;

public enum ReqLvl {
    JUNIOR (Color.GREEN),
    MID (Color.BLUE),
    SENIOR (Color.RED),
    ARCHITECT (Color.BLACK);

    Color color;

    ReqLvl(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
