package activityStarterCode.methodDecomposition;

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
public class PiggySpeakerClean {
    /**
     * @param word Word to be translated.
     * @return translation of the word into pig latin
     */
    public static String translateWord(String word) {
        int pos = -1;
        for (int i = 0; i < word.length(); i++) {
            if (isVowel(word.charAt((i)))) {
                pos = i;
                break;
            }
        }
        String result;
        if (pos == 0) {
            result = word + "way";
        } else if (pos == -1) {
            result = word + "ay";
        } else {
            result = word.substring(pos) + word.substring(0, pos) + "ay";
        }
        if (Character.isUpperCase(word.charAt(0))) {
            return Character.toUpperCase(result.charAt(0)) + result.substring(1).toLowerCase();
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
}
