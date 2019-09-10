package activityStarterCode.stringPractice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MakeSnakeTest {

    @Test
    public void mixedSsTest() {
        assertEquals("SSSnake sssoundsss", MakeSnake.makeSnake("Snake sounds"));
    }

    @Test
    public void emptyStringTest() {
        assertEquals("", MakeSnake.makeSnake(""));
    }
}
