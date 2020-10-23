package edu.macalester.graphics.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;

public class Button extends EmbeddedSwingComponent {
    private JButton button;

    public Button(String title) {
        this(new JButton(title));
    }

    private Button(JButton button) {
        super(button);
        this.button = button;
        button.setFocusable(false);

        // Some Windows versions of Java miscompute the button dimensions so the text
        // gets truncated, plus default Swing style on Windows is ugly.
        // We thus do manual styling + manual size computation. The style below brings
        // appearance and size roughly into parity across Mac and Windows.
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            int paddingV = 8, paddingH = 16;
            button.setBorder(new LineBorder(Color.LIGHT_GRAY));
            button.setBackground(Color.WHITE);

            String font = "Tahoma";
            int fontSize = 13;
            GraphicsText measure = new GraphicsText(button.getText());
            measure.setFont(font, FontStyle.PLAIN, fontSize);
            button.setFont(new Font(font, Font.PLAIN, fontSize));
            button.setPreferredSize(new Dimension(
                (int) Math.ceil(measure.getWidth() + paddingH * 2),
                (int) Math.ceil(measure.getHeight() + paddingV * 2)));
        }

        changed();
    }

    public void onClick(Runnable callback) {
        button.addActionListener(e -> {
            if (getCanvas() == null) {
                return;
            }
            getCanvas().performEventAction(callback);
        });
    }
}
