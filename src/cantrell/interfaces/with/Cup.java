package cantrell.interfaces.with;

import java.util.ArrayList;
import java.util.List;

public class Cup {
    private Liquid contents;

    public void fill(Liquid liquid) {
        this.contents = liquid;
    }

    public boolean contentsHot() {
        if (contents != null) {
            return contents.getTemperature() > 50;
        }
        return false;
    }

    public static void main(String[] args) {
        Cup cup = new Cup();
        Liquid water = new Water();
        cup.fill(water);

        Tea tea = new Tea();
        cup.fill(tea);
    }
}







