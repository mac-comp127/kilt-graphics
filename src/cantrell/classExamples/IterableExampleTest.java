package cantrell.classExamples;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class IterableExampleTest {
    private List<String>
        twoItems = Arrays.asList("cat", "dog"),
        threeItems = Arrays.asList("apple", "something", "zilch");

    @Test
    void testMergeFirstShorter() {
        assertEquals(
            Arrays.asList("apple", "cat", "dog", "something", "zilch"),
            IterableExample.mergeSorted(twoItems, threeItems));

    }

    @Test
    void testMergeSecondShorter() {
        assertEquals(
            Arrays.asList("apple", "cat", "dog", "something", "zilch"),
            IterableExample.mergeSorted(threeItems, twoItems));

    }

    @Test
    void testMergeBothSame() {
        assertEquals(
            Arrays.asList("cat", "cat", "dog", "dog"),
            IterableExample.mergeSorted(twoItems, twoItems));

    }

    @Test
    void testMergeBothEmpty() {
        assertEquals(
            Collections.emptyList(),
            IterableExample.mergeSorted(Collections.emptyList(), Collections.emptyList()));

    }

    @Test
    void testMergeFirstEmpty() {
        assertEquals(
            Arrays.asList("apple", "something", "zilch"),
            IterableExample.mergeSorted(Collections.emptyList(), threeItems));

    }

    @Test
    void testMergeSecondEmpty() {
        assertEquals(
            Arrays.asList("apple", "something", "zilch"),
            IterableExample.mergeSorted(threeItems, Collections.emptyList()));

    }
}
