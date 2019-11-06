package activityStarterCode.streamActivity;

import java.util.Objects;
import java.util.Set;

public class Book  {
    private final String title;
    private final String author;
    private final Set<String> genres;
    private final int pageCount;

    public Book(String title, String author, Set<String> genres, int pageCount) {
        this.title = Objects.requireNonNull(title);
        this.author = Objects.requireNonNull(author);
        this.genres = Set.copyOf(genres);
        this.pageCount = pageCount;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public Set<String> getGenres() {
        return this.genres;
    }

    public int getPageCount() {
        return this.pageCount;
    }

    public String toString() {
        return this.title + " by " + this.author + " " + this.genres + " (" + this.pageCount + " pages)";
    }
}
