package org.mancuso.aoc2020;

import java.time.Duration;
import java.time.Instant;
import java.util.BitSet;

public abstract class AbstractDay {
    public boolean verbose = false;
    public String part1Result;
    public Duration part1Duration;
    public String part2Result;
    public Duration part2Duration;

    public abstract String name();

    public abstract String part1();

    public abstract String part2();

    public void println(String message) {
        if (verbose) {
            System.out.println(message);
        }
    }

    public void printf(String format, Object... args) {
        if (verbose) {
            System.out.printf(format, args);
        }
    }

    public void run() {
        Instant start = Instant.now();
        part1Result = part1();
        Instant end = Instant.now();
        part1Duration = Duration.between(start, end);
        start = Instant.now();
        part2Result = part2();
        end = Instant.now();
        part2Duration = Duration.between(start, end);
    }

    public String report() {
        String result = name() + ":\t";
        if (part1Result != null) {
            result += "Part1 [" + part1Duration.toMillis() + "ms]:\t" + part1Result;
        } else {
            result += "Part1 not run";
        }
        result += "\t";
        if (part2Result != null) {
            result += "Part2 [" + part2Duration.toMillis() + "ms]:\t" + part2Result;
        } else {
            result += "Part2 not run";
        }
        return result;
    }

}
