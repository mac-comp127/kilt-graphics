package activityStarterCode.streamActivity;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@SuppressWarnings("WeakerAccess")
public class LibrarySolution {
    private final List<Book> books;

    public LibrarySolution(List<Book> books) {
        this.books = books;
    }

    /**
     * Task 1: Finds the titles of all the books by the given author
     */
    public List<String> findTitlesByAuthor(String authorName) {
        return books.stream()
            .filter(d -> d.getAuthor().equals(authorName))
            .map(Book::getTitle)
            .collect(toList());
    }

    /**
     * Task 2: Find the titles of the books with more than the given minimum number of pages.
     */
    public List<String> findTitlesOfLongBooks(int minPageCount) {
        List<String> list = new ArrayList<>();
        for (Book book : books) {
            if (book.getPageCount() > minPageCount) {
                list.add(book.getTitle());
            }
        }
        return list;
    }

    /**
     * Task 3: Describes the titles and authors of all the books in the given genre.
     */
    public List<String> findTitlesAndAuthorsInGenre(String genre) {
        return books.stream()
            .filter(d -> d.getGenres().contains(genre))
            .map(d -> d.getTitle() + " by " + d.getAuthor())
            .collect(toList());
    }

    /**
     * Task 4: How many books in the given genre does the library have?
     */
    public long countBooksInGenre(String genre) {
        return books.stream()
            .filter(d -> d.getGenres().contains(genre))
            .count();
    }

    /**
     * Task 5: What are the n shortest books in the library?
     */
    public List<Book> findShortestBooks(int n) {
        List<Book> booksByLength = new ArrayList<>(books);
        booksByLength.sort(Comparator.comparing(Book::getPageCount));
        return booksByLength.subList(0, Math.min(n, booksByLength.size()));
    }

    /**
     * Task 6: Finds all books in the given genre, sorted from shortest to longest.
     */
    public List<Book> findBooksInGenreSortedByLength(String genre) {
        return books.stream()
            .filter(book -> book.getGenres().contains(genre))
            .sorted(Comparator.comparing(Book::getPageCount))
            .collect(toList());
    }

    /**
     * Task 7: What is the book with the shortest title?
     */
    public Book findBookWithShortestTitle() {
        Book winner = null;
        int winnerLength = Integer.MAX_VALUE;
        for (Book book : books) {
            if (book.getTitle().length() < winnerLength) {
                winner = book;
                winnerLength = book.getTitle().length();
            }
        }
        return winner;
    }

    /**
     * Prints all books in the library to STDOUT.
     */
    public void printLibrary() {
        books.forEach(System.out::println);
    }

    // ––––––––––––  Special bonus challenges! ––––––––––––

    /**
     * What is the average number of pages of books in the library?
     * @return 0 if there are no books.
     */
    public double computeAverageBookLength() {
        if (books.isEmpty()) {
            return 0;
        }

        long totalPages = 0;
        for (Book book : books) {
            totalPages += book.getPageCount();
        }
        return (double) totalPages / books.size();
    }

    /**
     * Has the given author written any books in the given genre?
     *
     * Hint: Use anyMatch()
     */
    public boolean hasAuthorWrittenInGenre(String author, String genre) {
        return books.stream()
            .filter(book -> book.getAuthor().equals(author))
            .anyMatch(book -> book.getGenres().contains(genre));
    }

    /**
     * Gives a set containing all authors in the books.
     *
     * A Set is a bit like a List, with two differences:
     *
     * - It contains no duplicates; an element can only be in a set once.
     * - It does not preserve the order of the elements. They might not come out in the same order
     *   you put them in.
     *
     * Note: If you want to make a new mutable Set, use new HashSet<>().
     */
    public Set<String> getAllAuthors() {
        Set<String> result = new HashSet<>();
        for (Book book : books) {
            result.add(book.getAuthor());
        }
        return result;
    }

    /**
     * What are the names of all the authors who have written in the given genre?
     */
    public Set<String> allAuthorsInGenre(String genre) {
        return books.stream()
            .filter(book -> book.getGenres().contains(genre))
            .map(Book::getAuthor)
            .collect(toSet());
    }

    /**
     * What are all the different genres in the library?
     *
     * Hint: flatMap() is like map(), except the lambda returns a stream, and all the
     *       elements in that returned stream feed into the larger stream individually.
     */
    public Set<String> getAllGenres() {
        return books.stream()
            .flatMap(book -> book.getGenres().stream())
            .collect(toSet());
    }
}
