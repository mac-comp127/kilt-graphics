package comp127graphics.ui;

import javax.swing.JButton;

public class Button extends EmbeddedSwingComponent {
    private JButton button;

    public static Button withTitle(String title) {
        return new Button(new JButton(title));
    }

    private Button(JButton button) {
        super(button);
        this.button = button;
        button.setSize(button.getPreferredSize());
    }
}
