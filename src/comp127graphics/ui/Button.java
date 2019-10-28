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
        changed();
    }

    public void onClick(Runnable callback) {
        button.addActionListener(e ->
            getCanvas().performEventAction(callback));
    }
}
