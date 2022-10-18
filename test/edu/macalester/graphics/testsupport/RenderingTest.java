package edu.macalester.graphics.testsupport;

import static edu.macalester.graphics.testsupport.RenderingTestMode.HIT_TEST;
import static edu.macalester.graphics.testsupport.RenderingTestMode.PLAIN;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.image.BufferedImage;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

import edu.macalester.graphics.Point;

/**
 * Add this annotation to a method in a GraphicsObjectTestSuite to render the graphics object after
 * the method completes, optionally in a variety of different ways, and then compare the resulting
 * image(s) to previous test runs.
 * 
 * This annotation implies @Test.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(RenderingTestHandler.class)
@Test
public @interface RenderingTest {
    String
        OS_NAME_VERBOSE = System.getProperty("os.name").toLowerCase().replaceAll(" ", "_"),
        OS_NAME = OS_NAME_VERBOSE.split("_")[0];

    /**
     * A @RenderingTest can render the graphics object with a variety of different options.
     * Rendering mdoes run in the order the annotation specifies them. Note that some of the modes
     * have side effects that may affect subsequence modes, e.g. if you specify STROKED (which enables
     * the stroke) and then PLAIN (which changes nothing), the object will still be stroked.
     */
    RenderingTestMode[] modes() default { PLAIN, HIT_TEST };

    int width() default 100;
    int height() default 100;

    double tolerance() default 10;

    boolean osSpecificImageComparison() default false;
}

class RenderingTestHandler implements AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        var suite = getGraphicsObjectTestSuite(context);
        RenderingTest renderingOptions = context.getRequiredTestMethod().getAnnotation(RenderingTest.class);
        String osSuffix = renderingOptions.osSpecificImageComparison() ? "-" + RenderingTest.OS_NAME : "";

        assertAll(
            Arrays.stream(renderingOptions.modes()).map(mode -> () -> {
                Point imageSize = new Point(renderingOptions.width(), renderingOptions.height());
                var actualImage = new BufferedImage(
                    (int) Math.round(imageSize.getX()),
                    (int) Math.round(imageSize.getY()),
                    BufferedImage.TYPE_INT_ARGB);

                mode.render(actualImage, suite.getGraphicsObject());

                new ImageComparison(
                    context.getRequiredTestClass().getSimpleName(),
                    context.getRequiredTestMethod().getName() + "-" + mode.name().toLowerCase() + osSuffix,
                    actualImage,
                    renderingOptions.tolerance()
                ).compare();
            })
        );
    }

    private GraphicsObjectTestSuite getGraphicsObjectTestSuite(ExtensionContext context) {
        var testInstance = context.getRequiredTestInstance();
        if (!(testInstance instanceof GraphicsObjectTestSuite)) {
            fail(context.getRequiredTestMethod().getName()
                + " cannot be a @RenderingTest, because "
                + context.getRequiredTestClass().getSimpleName()
                + " does not implement "
                + GraphicsObjectTestSuite.class.getSimpleName());
        }
        return (GraphicsObjectTestSuite) testInstance;
    }
}
