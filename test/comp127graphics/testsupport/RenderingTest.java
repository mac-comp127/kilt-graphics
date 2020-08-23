package comp127graphics.testsupport;

import static comp127graphics.testsupport.RenderingTestMode.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

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
    /**
     * A @RenderingTest can render the graphics object with a variety of different options.
     * Rendering mdoes run in the order the annotation specifies them. Note that some of the modes
     * have side effects that may affect subsequence modes, e.g. if you specify STROKED (which enables
     * the stroke) and then PLAIN (which changes nothing), the object will still be stroked.
     */
    RenderingTestMode[] modes() default { PLAIN, STROKED, FILLED, FILLED_AND_STROKED, HIT_TEST };

    double tolerance() default 10;
}

class RenderingTestHandler implements AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        RenderingTest renderingOptions = context.getRequiredTestMethod().getAnnotation(RenderingTest.class);
        for (var mode : renderingOptions.modes()) {
            new ImageComparison(context, mode.name().toLowerCase(), mode, renderingOptions.tolerance()).compare();
        }
    }
}
