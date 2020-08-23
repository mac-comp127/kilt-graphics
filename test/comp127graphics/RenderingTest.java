package comp127graphics;

import static comp127graphics.TestRenderingMode.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(RenderingTestHandler.class)
@Test
public @interface RenderingTest {
    TestRenderingMode[] modes() default { PLAIN, STROKED, FILLED, FILLED_AND_STROKED, HIT_TEST };
}

class RenderingTestHandler implements AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        for (var mode : context.getRequiredTestMethod().getAnnotation(RenderingTest.class).modes()) {
            new ImageComparison(context, mode.name().toLowerCase(), mode).compare();
        }
    }
}
