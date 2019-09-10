package activityStarterCode.stringPractice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MakeEmphaticTest {

    @Test
    public void longStringTest() {
        assertEquals("EMPHASIS!!!", MakeEmphatic.makeEmphatic("emphasis"));
    }


    @Test
    public void shortStringTest() {
        assertEquals("I!!!", MakeEmphatic.makeEmphatic("i"));
    }

    @Test
    public void emptyStringTest() {
        assertEquals("!!!", MakeEmphatic.makeEmphatic(""));
    }
}
