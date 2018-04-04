package cantrell.mastermind;

import java.awt.Color;

public class ColorSet {
    private Color color0;
    private Color color1;
    private Color color2;
    private Color color3;

    public ColorSet(Color color0, Color color1, Color color2, Color color3) {
        this.color0 = color0;
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
    }

    public Color getColor0() {
        return color0;
    }

    public Color getColor1() {
        return color1;
    }

    public Color getColor2() {
        return color2;
    }

    public Color getColor3() {
        return color3;
    }
}
