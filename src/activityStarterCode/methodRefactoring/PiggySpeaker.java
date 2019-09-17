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
 * The sentence "Pig Latin is hard to speak." is written as "Igpay Atinlay isway ardhay otay eakspay."
 *
 * Note that we preserve case.
 *
 * @author Shilad
 */
public class PiggySpeaker {

    /**
     * @param phrase Phrase to be translated
     * @return The translation of the phrase into pig latin. Each white-space-separated token
     * is treated as a word and individually translated. Returned tokens are space-separated.
     */
    public static String translatePhrase(String phrase) {
        String result = "";
        for (String word : phrase.split("\\s+")) {
            if (result.length() > 0) {
                result += " ";
            }
            int pos = -1;
            for (int i = 0; i < word.length(); i++) {
                boolean v = false;
                if (Character.toLowerCase(word.charAt(i)) == 'a') {
                    v = true;
                } else {
                    v = v;
                }
                if (Character.toLowerCase(word.charAt(i)) == 'e') {
                    v = true;
                } else {
                    v = v;
                }
                if (Character.toLowerCase(word.charAt(i)) == 'i') {
                    v = true;
                } else {
                    v = v;
                }
                if (Character.toLowerCase(word.charAt(i)) == 'o') {
                    v = true;
                } else {
                    v = v;
                }
                if (Character.toLowerCase(word.charAt(i)) == 'u') {
                    v = true;
                } else {
                    v = v;
                }
                if (Character.toLowerCase(word.charAt(i)) == 'y') {
                    v = true;
                } else {
                    v = v;
                }
                if (v) {
                    pos = i;
                    break;
                }
            }
            String r;
            if (pos == 0) {
                r = word + "way";
            } else if (pos == -1) {
                r = word + "ay";
            } else if (pos == 1) {
                char first = word.charAt(0);
                String rest = word.substring(1);
                r = rest + first + "ay";
            } else if (pos == 2) {
                String firstTwo = word.substring(0, 2);
                String rest = word.substring(2);
                r = rest + firstTwo + "ay";
            } else { // three or more
                r = word.substring(pos) + word.substring(0, pos) + "ay";
            }
            char firstNew = r.charAt(0);
            char firstOrig = word.charAt(0);
            String restNew = r.substring(1);
            if (Character.isUpperCase(firstOrig)) {
                firstNew = Character.toUpperCase(firstNew);
            }
            String newWord = (firstNew + restNew.toLowerCase());
            result += newWord;
        }
        return result;
    }
}
