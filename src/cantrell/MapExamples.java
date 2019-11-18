package cantrell;

import java.util.*;

public class MapExamples {
    public MapExamples() {
    }

    public static void main(String[] args) {
        Book
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

        Map<String,Book> booksByISBN = new HashMap<>();
        booksByISBN.put("0007149832", yiddish_policeman);
        booksByISBN.put("978-0007149834", yiddish_policeman);
        booksByISBN.put("5623423452", poisonwood_bible);
        booksByISBN.put("1453425622", hobbit);
        booksByISBN.put("marbles and chewing gum", null);
        System.out.println(booksByISBN);
        System.out.println(booksByISBN.get("5623423452"));
        System.out.println(booksByISBN.get("marbles and chewing gum"));
        System.out.println(booksByISBN.containsKey("marbles and chewing gum"));
    }
}













