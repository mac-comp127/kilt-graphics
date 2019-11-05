package activityStarterCode.debugActivity;

import comp127graphics.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dkluver on 2017-11-17. Updated by pcantrell 2019-11-5.
 */
public class MacalesterLines extends GraphicsGroup {
    public void draw() {
        List<Line> lines = new ArrayList<>();
        Line line = new Line(0, 0, 0, 0);

        line.setStartPosition(-40, 0);
        line.setEndPosition(360, 400);
        line.setStrokeColor(new Color(0xA571C5E8, true));
        line.setStrokeWidth(60);
        lines.add(line);

        line.setStartPosition(100, 40);
        line.setEndPosition(460, 400);
        line.setStrokeColor(new Color(0xA5002033, true));
        line.setStrokeWidth(30);
        lines.add(line);

        line.setStartPosition(100, 430);
        line.setEndPosition(460, 70);
        line.setStrokeColor(new Color(0xA5002033, true));
        line.setStrokeWidth(30);
        lines.add(line);

        line.setStartPosition(110, 369);
        line.setEndPosition(470, 9);
        line.setStrokeColor(new Color(0xA55B6770, true));
        line.setStrokeWidth(10);
        lines.add(line);

        line.setStartPosition(0, 430);
        line.setEndPosition(360, 70);
        line.setStrokeColor(new Color(0xA5EF4F26, true));
        line.setStrokeWidth(30);
        lines.add(line);

        lines.forEach(this::add);
    }

    public static void main(String[] args) {
        CanvasWindow window = new CanvasWindow("Macalester lines", 400, 400);
        MacalesterLines fl = new MacalesterLines();
        fl.draw();
        window.add(fl);
    }
}
