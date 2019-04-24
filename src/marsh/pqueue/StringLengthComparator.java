package marsh.pqueue;

import java.util.Comparator;

public class StringLengthComparator implements Comparator<String> {

    /**
     * Compares two strings by length. Will return negative number if s2 longer than s1,
     * zero if two strings are equal, and positive number if s1 longer than s2.
     *
     * @param s1 First string to be compared
     * @param s2 Second string to be compared
     * @return Length of s1 minus length of s2
     */
    @Override
    public int compare(String s1, String s2) {
        return s1.length() - s2.length();
    }
}
