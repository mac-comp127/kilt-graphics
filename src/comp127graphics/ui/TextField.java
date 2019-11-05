package comp127graphics.ui;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Color;
import java.awt.Dimension;
import java.util.function.Consumer;

public class TextField extends EmbeddedSwingComponent {
    private final JTextField field;

    public TextField() {
        super(new JTextField());
        this.field = (JTextField) getEmbeddedComponent();
        field.setMinimumSize(new Dimension(100, 0));
        changed();
    }

    public String getText() {
        return field.getText();
    }

    public void setText(String text) {
        field.setText(text);
    }

    public void onChange(Consumer<String> callback) {
        field.getDocument().addDocumentListener(
            new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    triggerCallback();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    triggerCallback();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    triggerCallback();
                }

                private void triggerCallback() {
                    if (getCanvas() == null) {
                        return;
                    }

                    getCanvas().performEventAction(() ->
                        callback.accept(field.getText()));
                }
            }
        );
    }

    @Override
    public String toString() {
        return "Text field at " + getPosition() + " with text " + getText();
    }

    public void setBackground(Color color) {
        field.setBackground(color);
    }
}
