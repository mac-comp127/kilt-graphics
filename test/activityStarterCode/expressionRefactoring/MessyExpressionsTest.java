package activityStarterCode.expressionRefactoring;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessyExpressionsTest {
    @Test
    void sumUpTo() {
        assertEquals(0, MessyExpressions.sumUpTo(0));
        assertEquals(1, MessyExpressions.sumUpTo(1));
        assertEquals(6, MessyExpressions.sumUpTo(3));
        assertEquals(45, MessyExpressions.sumUpTo(9));
    }

    @Test
    void isEmphatic() {
        assertTrue(MessyExpressions.isEmphatic("Wow!"));
        assertTrue(MessyExpressions.isEmphatic("Huh?!?"));
        assertTrue(MessyExpressions.isEmphatic("!olleH"));
        assertFalse(MessyExpressions.isEmphatic("Huh."));
        assertFalse(MessyExpressions.isEmphatic(""));
    }

    @Test
    void trimParentheses() {
        assertEquals("hi", MessyExpressions.trimParentheses("(hi)"));
        assertEquals("(hi", MessyExpressions.trimParentheses("(hi"));
        assertEquals("hi)", MessyExpressions.trimParentheses("hi)"));
        assertEquals("(hi)", MessyExpressions.trimParentheses("((hi))"));
        assertEquals("", MessyExpressions.trimParentheses(""));
        assertEquals("", MessyExpressions.trimParentheses("()"));
    }

    @Test
    void isInBounds() {
        assertTrue(MessyExpressions.isInBounds(2, 1, 3));
        assertTrue(MessyExpressions.isInBounds(5, 5, 12));
        assertTrue(MessyExpressions.isInBounds(7, -3, 7));
        assertFalse(MessyExpressions.isInBounds(2, 3, 10));
        assertFalse(MessyExpressions.isInBounds(11, 3, 10));
    }

    @Test
    void nextCollatz() {
        assertEquals(3, MessyExpressions.nextCollatz(6));
        assertEquals(22, MessyExpressions.nextCollatz(7));
        assertEquals(0, MessyExpressions.nextCollatz(0));
        assertEquals(-1, MessyExpressions.nextCollatz(-2));
        assertEquals(-8, MessyExpressions.nextCollatz(-3));
    }

    @Test
    void isJustScreaming() {
        assertTrue(MessyExpressions.isJustScreaming("AAAAAAA!!!!"));
        assertTrue(MessyExpressions.isJustScreaming("aa!! aaa!! a! a! !!!!"));
        assertTrue(MessyExpressions.isJustScreaming("AaAaAaA"));
        assertFalse(MessyExpressions.isJustScreaming("aa!! eeek!!!"));
        assertFalse(MessyExpressions.isJustScreaming("AAAA!!?!!!"));
        assertFalse(MessyExpressions.isJustScreaming("Aaaa. AAAA."));
        assertTrue(MessyExpressions.isJustScreaming("!"));
        assertTrue(MessyExpressions.isJustScreaming(" "));
        assertTrue(MessyExpressions.isJustScreaming(""));
    }

    @Test
    void removeAccents() {
        assertEquals("cauliflower education",
            MessyExpressions.removeAccents("cáulíflower édúcatión"));
        assertEquals(".ᦗe1$✾@aࢨoᎉ", MessyExpressions.removeAccents(".ᦗé1$✾@áࢨóᎉ"));
        assertEquals("", MessyExpressions.removeAccents(""));
    }

    @Test
    void containsOwnLength() {
        assertTrue(MessyExpressions.containsOwnLength("th15 one does!!"));
        assertFalse(MessyExpressions.containsOwnLength("th15 one doesn’t"));
        assertTrue(MessyExpressions.containsOwnLength("1234"));
        assertTrue(MessyExpressions.containsOwnLength("321"));
        assertTrue(MessyExpressions.containsOwnLength("1"));
        assertFalse(MessyExpressions.containsOwnLength(""));
    }
}
