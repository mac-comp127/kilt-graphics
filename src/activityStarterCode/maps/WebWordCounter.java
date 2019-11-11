package activityStarterCode.maps;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;


/**
 * Activity to count words at some URL.
 * @author Shilad Sen
 */
public class WebWordCounter {
    private static Pattern SPLIT_PATTERN = Pattern.compile("[^a-zA-Z]+");
    private static String TEST_URL = "http://textfiles.com/politics/all11.txt";

    /**
     * Downloads data at some URL and separates it into lower case words.
     * Adapted from https://stackoverflow.com/questions/4328711
     * Any non-alphanumeric characters are treated as word separators.
     */
    private static List<String> urlToWords(String url) {
        try {
            InputStream input = new URL(url).openStream();
            Scanner scanner = new Scanner(input, "UTF-8");
            String text = scanner.useDelimiter("\\A").next();
            String [] tokens = SPLIT_PATTERN.split(text.toLowerCase());
            return Arrays.asList(tokens);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read from URL " + url + ": " + e.getMessage(), e);
        }
    }

    /**
     * Returns true iff the word is a commonly used word and shouldn't be counted.
     */
    private static boolean isStopWord(String word) {
        return word.length() <= 3 || STOP_WORDS.contains(word.toLowerCase());
    }


    public static void main(String [] args) {
        // Step 1 Create new map for storing word counts

        // Step 2: Retrieve the words associated with the data at some URL
        // Hint: You can use TEST_URL, which contains the constitution.

        // Step 3: Loop over every word and increment the count of the word by 1.
        // Make sure to avoid stop words (use isStopWord()))
        // Hint: You will need to be cautious about detecting if this is the first
        // time you have seen the word.

        // Step 4: Print out all words and their associated counts for words that
        // appear greater then some frequence (for example, 5).
    }


    public static Set<String> STOP_WORDS = Set.of(
            "a", "about", "above", "across", "after", "afterwards", "again", "against", "all",
            "almost", "alone", "along", "already", "also","although","always","am","among",
            "amongst", "amoungst", "amount",  "an", "and", "another", "any","anyhow","anyone",
            "anything","anyway", "anywhere", "are", "around", "as",  "at", "back","be","became",
            "because","become","becomes", "becoming", "been", "before", "beforehand", "behind",
            "being", "below", "beside", "besides", "between", "beyond", "bill", "both", "bottom",
            "but", "by", "call", "can", "cannot", "cant", "co", "con", "could", "couldnt", "cry",
            "de", "describe", "detail", "do", "done", "down", "due", "during", "each", "eg", "eight",
            "either", "eleven","else", "elsewhere", "empty", "enough", "etc", "even", "ever",
            "every", "everyone", "everything", "everywhere", "except", "few", "fifteen", "fify",
            "fill", "find", "fire", "first", "five", "for", "former", "formerly", "forty", "found",
            "four", "from", "front", "full", "further", "get", "give", "go", "had", "has", "hasnt",
            "have", "he", "hence", "her", "here", "hereafter", "hereby", "herein", "hereupon",
            "hers", "herself", "him", "himself", "his", "how", "however", "hundred", "ie", "if",
            "in", "inc", "indeed", "interest", "into", "is", "it", "its", "itself", "keep", "last",
            "latter", "latterly", "least", "less", "ltd", "made", "many", "may", "me", "meanwhile",
            "might", "mill", "mine", "more", "moreover", "most", "mostly", "move", "much", "must", "my",
            "myself", "name", "namely", "neither", "never", "nevertheless", "next", "nine", "no", "nobody",
            "none", "noone", "nor", "not", "nothing", "now", "nowhere", "of", "off", "often", "on", "once",
            "one", "only", "onto", "or", "other", "others", "otherwise", "our", "ours", "ourselves", "out",
            "over", "own","part", "per", "perhaps", "please", "put", "rather", "re", "same", "see", "seem",
            "seemed", "seeming", "seems", "serious", "several", "she", "should", "show", "side", "since",
            "sincere", "six", "sixty", "so", "some", "somehow", "someone", "something", "sometime", "sometimes",
            "somewhere", "still", "such", "system", "take", "ten", "than", "that", "the", "their", "them",
            "themselves", "then", "thence", "there", "thereafter", "thereby", "therefore", "therein",
            "thereupon", "these", "they", "thick", "thin", "third", "this", "those", "though", "three",
            "through", "throughout", "thru", "thus", "to", "together", "too", "top", "toward", "towards",
            "twelve", "twenty", "two", "un", "under", "until", "up", "upon", "us", "very", "via", "was",
            "we", "well", "were", "what", "whatever", "when", "whence", "whenever", "where", "whereafter",
            "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither",
            "who", "whoever", "whole", "whom", "whose", "why", "will", "with", "within", "without", "would",
            "yet", "you", "your", "yours", "yourself", "yourselves"
    );
}
