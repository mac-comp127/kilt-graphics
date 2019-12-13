package comp127graphics.ui;

import javax.swing.JButton;

public class Button extends EmbeddedSwingComponent {
    private JButton button;

    public Button(String title) {
        this(new JButton(title));
    }

    private Button(JButton button) {
        super(button);
        this.button = button;
        button.setFocusable(false);
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
