package activityStarterCode.methodDecomposition;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class PiggySpeakerTest {

    @Test
    public void translatePhrase() {
        assertEquals("Igpay Atinlay isway ardhay otay eakspay",
                PiggySpeaker.translatePhrase("Pig Latin is hard to speak"));
        assertEquals("Ompcay 127ay isway ymay avoritefay assclay",
                PiggySpeaker.translatePhrase("COMP 127 is my favorite class"));
        assertEquals("Acalestermay isway ommittedcay otay eingbay away eeminentpray",
                PiggySpeaker.translatePhrase("Macalester is committed to being a preeminent"));
        assertEquals("Acalestermay Athematics,may Atistics,stay andway Omputercay Iencescay",
                PiggySpeaker.translatePhrase("Macalester Mathematics, Statistics, and Computer Science"));
    }

    /**
     * This test may be helpful to you as you refactor.
     * Uncomment it and run it if it is helpful.
     */
//    @Test
//    public void testIsVowel() {
//        assertTrue(PiggySpeaker.isVowel('A'));
//        assertTrue(PiggySpeaker.isVowel('a'));
//        assertTrue(PiggySpeaker.isVowel('Y'));
//        assertTrue(PiggySpeaker.isVowel('y'));
//        assertFalse(PiggySpeaker.isVowel('Z'));
//        assertFalse(PiggySpeaker.isVowel('!'));
//        assertFalse(PiggySpeaker.isVowel('3'));
//    }

    /**
     * This test may be helpful to you as you refactor.
     * Uncomment it and run it if it is helpful.
     */
//    @Test
//    public void testTranslateWord() {
//        assertEquals("Elcomeway", PiggySpeaker.translateWord("Welcome"));
//        assertEquals("elcomeway", PiggySpeaker.translateWord("welcome"));
//        assertEquals("Ellohay", PiggySpeaker.translateWord("Hello"));
//        assertEquals("ellohay", PiggySpeaker.translateWord("hello"));
//        assertEquals("appyhay", PiggySpeaker.translateWord("happy"));
//        assertEquals("Appyhay", PiggySpeaker.translateWord("Happy"));
//        assertEquals("awesomeway", PiggySpeaker.translateWord("awesome"));
//    }
}
