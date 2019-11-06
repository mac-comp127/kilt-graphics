package activityStarterCode.streamActivity;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    private final Book
        american_gods      = new Book("American Gods", "Neil Gaiman", Set.of("fiction", "fantasy"), 325),
        country_between_us = new Book("The Country Between Us", "Carolyn Forché", Set.of("poetry"), 64),
        dream_songs        = new Book("The Dream Songs", "John Berryman", Set.of("poetry"), 133),
        dud_avocado        = new Book("The Dud Avocado", "Elaine Dundy", Set.of("fiction"), 205),
        foundation         = new Book("Foundation", "Isaac Asimov", Set.of("fiction", "scifi"), 375),
        h2g2               = new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", Set.of("fiction", "scifi"), 421),
        harry_potter_1     = new Book("Harry Potter and the Sorceror’s Stone", "J.K. Rowling", Set.of("fiction", "fantasy"), 320),
        harry_potter_2     = new Book("Harry Potter and the Chamber of Secrets", "J.K. Rowling", Set.of("fiction", "fantasy"), 341),
        hobbit             = new Book("The Hobbit", "J.R.R. Tolkien", Set.of("fiction", "fantasy"), 350),
        jazz               = new Book("Jazz", "Toni Morrison", Set.of("fiction"), 229),
        june_rise          = new Book("The June Rise", "Bill Tremblay", Set.of("poetry", "fiction"), 240),
        moon_crossing      = new Book("Moon Crossing Bridge", "Tess Gallagher", Set.of("poetry"), 98),
        orwell1984         = new Book("1984", "George Orwell", Set.of("fiction"), 240),
        parable_sower      = new Book("Parable of the Sower", "Octavia Butler", Set.of("fiction", "scifi"), 299),
        poisonwood_bible   = new Book("The Poisonwood Bible", "Barbara Kingsolver", Set.of("fiction"), 545),
        pond               = new Book("Pond", "Claire-Louise Bennett", Set.of("fiction"), 126),
        war_poems          = new Book("War Poems", "John Hollander", Set.of("poetry"), 100),
        yiddish_policeman  = new Book("The Yiddish Policeman's Union", "Michael Chabon", Set.of("fiction"), 202);

    private final Library library = new Library(List.of(
        american_gods,
        country_between_us,
        dream_songs,
        dud_avocado,
        foundation,
        h2g2,
        harry_potter_1,
        harry_potter_2,
        hobbit,
        jazz,
        june_rise,
        moon_crossing,
        orwell1984,
        parable_sower,
        poisonwood_bible,
        pond,
        war_poems,
        yiddish_policeman
    ));

    private final Library emptyLibrary = new Library(List.of());

    @Test
    void findTitlesInGenre() {
        assertEquals(
            List.of("Harry Potter and the Sorceror’s Stone", "Harry Potter and the Chamber of Secrets"),
            library.findTitlesByAuthor("J.K. Rowling"));

        assertEquals(
            List.of("Pond"),
            library.findTitlesByAuthor("Claire-Louise Bennett"));

        assertEquals(
            List.of(),
            library.findTitlesByAuthor("Ludicrous P. Eddingsbuff"));

        assertEquals(List.of(), emptyLibrary.findTitlesByAuthor(""));
    }

    @Test
    void findTitlesOfLongBooks() {
        assertEquals(
            List.of("The Hitchhiker's Guide to the Galaxy", "The Poisonwood Bible"),
            library.findTitlesOfLongBooks(400));

        assertEquals(
            List.of("Foundation", "The Hitchhiker's Guide to the Galaxy", "The Poisonwood Bible"),
            library.findTitlesOfLongBooks(350));

        assertEquals(
            List.of(),
            library.findTitlesOfLongBooks(50000));

        assertEquals(List.of(), emptyLibrary.findTitlesOfLongBooks(1));
    }

    @Test
    void findTitlesAndAuthorsInGenre() {
        assertEquals(
            List.of(
                "The Country Between Us by Carolyn Forché",
                "The Dream Songs by John Berryman",
                "The June Rise by Bill Tremblay",
                "Moon Crossing Bridge by Tess Gallagher",
                "War Poems by John Hollander"
            ),
            library.findTitlesAndAuthorsInGenre("poetry"));

        assertEquals(
            List.of(),
            library.findTitlesAndAuthorsInGenre("recipes"));

        assertEquals(List.of(), emptyLibrary.findTitlesAndAuthorsInGenre("poetry"));
    }

    @Test
    void countBooksInGenre() {
        assertEquals(14, library.countBooksInGenre("fiction"));
        assertEquals(3, library.countBooksInGenre("scifi"));
        assertEquals(0, library.countBooksInGenre("random digits"));
        assertEquals(0, emptyLibrary.countBooksInGenre("fiction"));
    }

    @Test
    void findShortestBooks() {
        assertEquals(
            List.of(country_between_us),
            library.findShortestBooks(1));

        assertEquals(
            List.of(country_between_us, moon_crossing),
            library.findShortestBooks(2));

        assertEquals(
            List.of(),
            library.findShortestBooks(0));

        assertEquals(List.of(), emptyLibrary.findShortestBooks(1));
    }

    @Test
    void findBooksInGenreSortedByLength() {
        assertEquals(
            List.of(harry_potter_1, american_gods, harry_potter_2, hobbit),
            library.findBooksInGenreSortedByLength("fantasy"));

        assertEquals(
            List.of(),
            library.findBooksInGenreSortedByLength("shipping logs"));

        assertEquals(List.of(), emptyLibrary.findBooksInGenreSortedByLength("fantasy"));
    }

    @Test
    void findBookWithShortestTitle() {
        assertEquals(jazz, library.findBookWithShortestTitle());
        assertNull(emptyLibrary.findBookWithShortestTitle());
    }

    @Test
    void printLibrary() {
        library.printLibrary();
        emptyLibrary.printLibrary();
    }

    @Test
    void computeAverageBookLength() {
        assertEquals(256.27, library.computeAverageBookLength(), 0.01);
        assertEquals(0, emptyLibrary.computeAverageBookLength());
    }

    @Test
    void hasAuthorWrittenInGenre() {
        assertTrue(library.hasAuthorWrittenInGenre("Bill Tremblay", "poetry"));
        assertTrue(library.hasAuthorWrittenInGenre("Bill Tremblay", "fiction"));
        assertFalse(library.hasAuthorWrittenInGenre("John Berryman", "fiction"));
        assertFalse(library.hasAuthorWrittenInGenre("Snargle Q. Ingleflitz", "fiction"));
        assertFalse(library.hasAuthorWrittenInGenre("Elaine Dundy", "glossalalia"));
        assertFalse(emptyLibrary.hasAuthorWrittenInGenre("Bill Tremblay", "poetry"));
    }

    @Test
    void allAuthors() {
        assertEquals(
            Set.of(
                "Barbara Kingsolver", "Bill Tremblay", "Carolyn Forché", "Claire-Louise Bennett",
                "Douglas Adams", "Elaine Dundy", "George Orwell", "Isaac Asimov", "J.K. Rowling",
                "J.R.R. Tolkien", "John Berryman", "John Hollander", "Michael Chabon",
                "Neil Gaiman", "Octavia Butler", "Tess Gallagher", "Toni Morrison"),
            library.getAllAuthors());

        assertEquals(Set.of(), emptyLibrary.getAllAuthors());
    }

    @Test
    void allGenres() {
        assertEquals(
            Set.of("poetry", "fiction", "scifi", "fantasy"),
            library.getAllGenres());

        assertEquals(Set.of(), emptyLibrary.getAllGenres());
    }

    @Test
    void allAuthorsInGenre() {
        assertEquals(
            Set.of("Bill Tremblay", "Carolyn Forché", "John Berryman", "Tess Gallagher", "John Hollander"),
            library.allAuthorsInGenre("poetry"));

        assertEquals(
            Set.of("Douglas Adams", "Isaac Asimov", "Octavia Butler"),
            library.allAuthorsInGenre("scifi"));

        assertEquals(
            Set.of(),
            library.allAuthorsInGenre("dada"));

        assertEquals(Set.of(), emptyLibrary.allAuthorsInGenre("poetry"));
    }
}
