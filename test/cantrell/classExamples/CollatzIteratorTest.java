package cantrell.classExamples;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CollatzIteratorTest {
    @Test
    void findsRecord() {
        assertEquals(9, CollatzIterator.recordSettingSeed(10));
        assertEquals(54, CollatzIterator.recordSettingSeed(60));
    }

    @Test
    void maxCanBeRecord() {
        assertEquals(6, CollatzIterator.recordSettingSeed(6));
        assertEquals(9, CollatzIterator.recordSettingSeed(9));
    }

    @Test
    void smallMaxAlwaysReturns1() {
        // This is a special / degenerate case: CollatzIterator treats every
        // max less than 1 as if it were 1.
        assertEquals(1, CollatzIterator.recordSettingSeed(1));
        assertEquals(1, CollatzIterator.recordSettingSeed(0));
        assertEquals(1, CollatzIterator.recordSettingSeed(-10));
    }
}












