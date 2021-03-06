/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.mancuso.aoc2020;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day2 extends AbstractDay {
    public static class InvalidRuleException extends Exception {
    }

    public String name() {
        return "Day2";
    }

    static boolean between(int l, int r, int x) {
        return (x >= l && x <= r) || (x <= r && x >= l);
    }

    static boolean day1RuleTripped(String input) {
        int matches = 0;
        int l = 0;
        int r = 0;
        try {
            String[] parts = input.split(":");
            String rule = parts[0];
            String value = parts[1];
            String[] ruleParts = rule.split(" ");
            String countSpec = ruleParts[0];
            String letter = ruleParts[1];
            String[] counts = countSpec.split("-");
            l = Integer.parseInt(counts[0]);
            r = Integer.parseInt(counts[1]);
            if (letter.length() > 1) {
                System.out.println("Warning invalid rule: " + input);
            }
            Character c = letter.charAt(0);
            for (int i = 0; i < value.length(); i++) {
                if (value.charAt(i) == c) {
                    matches++;
                }
            }
        } catch (Exception ex) {
            System.out.println("Error on input: [" + input + "] -- " + ex.getMessage());
        }
        return between(l, r, matches);
    }

    static boolean day2RuleTripped(String input) {
        int matches = 0;
        int l = 0;
        int r = 0;
        try {
            String[] parts = input.split(":");
            String rule = parts[0];
            String value = parts[1].trim();
            String[] ruleParts = rule.split(" ");
            String countSpec = ruleParts[0];
            String letter = ruleParts[1];
            String[] counts = countSpec.split("-");
            l = Integer.parseInt(counts[0]);
            r = Integer.parseInt(counts[1]);
            if (letter.length() > 1) {
                System.out.println("Warning invalid rule: " + input);
            }
            Character c = letter.charAt(0);

            if(value.charAt(l-1)==c ^ value.charAt(r-1)==c) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Error on input: [" + input + "] -- " + ex.getMessage());
        }
        return false;
    }

    static int day1Validate(String input) {
        if (day1RuleTripped(input)) {
            return 1;
        }
        return 0;
    }

    static int day2Validate(String input) {
        if (day2RuleTripped(input)) {
            return 1;
        }
        return 0;
    }

    public int load(String filename, Function<String, Integer> validate) {
        Path path = Paths.get(filename);
        try (BufferedReader br = Files.newBufferedReader(path)) {
            return br.lines().map(validate).collect(Collectors.summingInt(x -> x));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String part1() {
        return String.valueOf(load("day2_app/input", Day2::day1Validate));
    }

    public String part2() {
        return String.valueOf(load("day2_app/input", Day2::day2Validate));
    }

}
