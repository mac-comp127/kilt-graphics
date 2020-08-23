package comp127graphics;

import static comp127graphics.testsupport.RenderingTestMode.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import comp127graphics.testsupport.GraphicsObjectTestSuite;
import comp127graphics.testsupport.RenderingTest;

public class GraphicsTextTest implements GraphicsObjectTestSuite {
    private GraphicsText text;

    @Override
    public GraphicsObject getGraphicsObject() {
        return text;
    }

    @RenderingTest(modes = { PLAIN, FILLED, HIT_TEST })
    void plain() {
        text = new GraphicsText("plax");
        assertChangedAtEachStep(
            () -> text.setPosition(30, 60)
        );
    }

    @RenderingTest(modes = { PLAIN })
    void styled() {
        text = new GraphicsText("zonk");
        assertChangedAtEachStep(
            () -> text.setFont("Arial", FontStyle.ITALIC, 16),
            () -> text.setCenter(30, 40),
            () -> text.setFontSize(32),
            () -> text.setFontStyle(FontStyle.BOLD)
        );
        assertEquals(74, text.getWidth(), 0.5);
        assertEquals(37, text.getHeight(), 0.5);
    }

    @RenderingTest(modes = { PLAIN })
    void changed() {
        text = new GraphicsText("plobble", 10, 50);
        assertChangedAtEachStep(
            () -> text.setFont("Arial", FontStyle.BOLD, 9),
            () -> text.setFont(FontStyle.BOLD, 20),
            () -> text.setText("zeep")
        );
    }

    @RenderingTest
    void unicode() {
        text = new GraphicsText("优雅");
        assertChangedAtEachStep(
            () -> text.setFont("Fang Song", FontStyle.PLAIN, 32),
            () -> text.setPosition(30, 60)
        );
        assertEquals("\u4f18\u96c5", text.getText());
    }

    @RenderingTest
    void nullString() {
        text = new GraphicsText(null, 50, 50);
    }

    @RenderingTest(modes = { PLAIN })
    void emptyString() {
        text = new GraphicsText("", 50, 50);
    }
}