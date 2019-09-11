package activityStarterCode.stringPractice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UppercaseFirstLetterTest {

    @Test
    public void uppercaseStringTest() {
        assertEquals("Uppercase", UppercaseFirstLetter.uppercaseFirstLetter("Uppercase"));
    }

    @Test
    public void longStringTest() {
        assertEquals("Uppercase", UppercaseFirstLetter.uppercaseFirstLetter("uppercase"));
    }

    @Test
    public void shortStringTest() {
        assertEquals("A", UppercaseFirstLetter.uppercaseFirstLetter("a"));
    }

    @Test
    public void emptyStringTest() {
        assertEquals("", UppercaseFirstLetter.uppercaseFirstLetter(""));
    }

    @Test
    public void sameLetterTest() {
        assertEquals("Eeee", UppercaseFirstLetter.uppercaseFirstLetter("eeee"));
    }

}
