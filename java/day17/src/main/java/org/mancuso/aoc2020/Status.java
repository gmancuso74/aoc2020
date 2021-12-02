package org.mancuso.aoc2020;

public enum Status implements Printable {
    INACTIVE('.'),
    ACTIVE('#');

    private final char printChar;

    private Status(char printChar) {
        this.printChar = printChar;
    }

    public String print() {
        return String.valueOf(printChar);
    }

    public static Status statusConverter(Character in) {
        if (in == '#') return Status.ACTIVE;
        return Status.INACTIVE;
    }

}
