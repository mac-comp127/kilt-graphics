package activityStarterCode.methodRefactoring;

/**
 * A class to speak pig latin that is obfuscated.
 *
 * The pig latin rules we use are based on https://web.ics.purdue.edu/~morelanj/RAO/prepare2.html
 *
 * 1. If a word starts with one or more consonants, put the consonants at
 *    the end of the word and add "ay."
 *      Example: Happy = appyh + ay = appyhay
 *      Example: Child = Ildch + ay = Ildchay
 * 2. If a word starts with a vowel add the word "way" at the end of the word.
 *      Example: Awesome = Awesome +way = Awesomeway
 * 3. If there are no vowels, add "ay"
 *      Example: 127 = 127 + ay = 127ay
 *
 * Sentences are translated word by word. For example, the sentence "Pig Latin is hard to speak"
 * is written as "Igpay Atinlay isway ardhay otay eakspay"
 *
 * Note that we preserve case at the starting of each word.
 *
 * @author Shilad
 */
@SuppressWarnings({"StringConcatenationInLoop", "WeakerAccess"})
public class PiggySpeakerClean2 {
    /**
     * @param word Word to be translated.
     * @return translation of the word into pig latin
     */
    public static String translateWord(String word) {
        int firstVowelPos = indexOfFirstVowel(word);

        String result;
        if (firstVowelPos == 0) {
            result = word + "way";
        } else if (firstVowelPos == -1) {
            result = word + "ay";
        } else {
            result = word.substring(firstVowelPos) + word.substring(0, firstVowelPos) + "ay";
        }

        if (isCapitalized(word)) {
            return capitalize(result);
        } else {
            return result.toLowerCase();
        }
    }

    /**
     * @param phrase Phrase to be translated
     * @return The translation of the phrase into pig latin. Each white-space-separated token
     * is treated as a word and individually translated. Returned tokens are space-separated.
     */
    public static String translatePhrase(String phrase) {
        String result = "";
        for (String w : phrase.split("\\s+")) {
            if (result.length() > 0) {
                result += " ";
            }
            result += translateWord(w);
        }
        return result;
    }

    /**
     * @param ch Character to be tested.
     * @return True if and only if the character is an upper- or lower-case vowel (including Y).
     */
    public static boolean isVowel(char ch) {
        char lch = Character.toLowerCase(ch);
        return (lch == 'a' || lch == 'e' || lch == 'i' || lch == 'o' || lch == 'u' || lch == 'y');
    }

    /**
     * Returns the index of the first vowel in the given string, or -1 if the string contains
     * no vowels.
     */
    private static int indexOfFirstVowel(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (isVowel(s.charAt((i)))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the given string with the first character converted to upper case, and the
     * remainder of the string converted to lower case.
     *
     * @param s A string with length ≥ 1
     */
    private static String capitalize(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase();
    }

    /**
     * Returns true if the first character of the given string is an upper case letter.
     *
     * @param s A string with length ≥ 1
     */
    private static boolean isCapitalized(String s) {
        return Character.isUpperCase(s.charAt(0));
    }
}
