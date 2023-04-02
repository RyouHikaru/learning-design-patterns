package com.tapioca.design.patterns;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Single Responsibility Principle (SRP)
 */
class Journal {
    private final List<String> entries = new ArrayList<>();
    private static int count = 0;

    public void addEntry(String text) {
        entries.add("" + (++count) + ": " + text);
    }

    public void removeEntry(int index) {
        entries.remove(index);
    }

    /*
    Note: These methods violates the SRP and should be moved to another class

    public void save(String filename) throws FileNotFoundException {
        try (PrintStream out = new PrintStream(filename)) {
            out.println(toString());
        }
    }

    public void load(String filename) {}
    public void load(URL url) {}

     */

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }
}

class Persistence {
    public void saveToFile(Journal journal, String filename, boolean overwrite) throws FileNotFoundException {
        if (overwrite || new File(filename).exists()) {
            try (PrintStream out = new PrintStream(filename)) {
                out.println(journal.toString());
            }
        }
    }

    public Journal load(String filename) { return null; }
    public Journal load(URL url) { return null; }
}

public class SRP {
    public static void main(String[] args) throws Exception {
        Journal journal = new Journal();
        journal.addEntry("I cried today");
        journal.addEntry("I ate a bug");

        Persistence persistence = new Persistence();
        String filename = "output\\journal.txt";
        persistence.saveToFile(journal, filename, true);

        Runtime.getRuntime().exec("notepad " + filename);
    }
}
