package org.mancuso.aoc2020;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Day7Test {

    Day7 day = new Day7();

    @Test
    public void testSmall() throws IOException {
        Path p = Paths.get("small");
        List<String> lines = Files.readAllLines(p);
//        day.verbose=true;
        Map<String, List<Day7.Content>> rules = day.parseRulesForContainment(lines);
        assertEquals(9, rules.size());
        List<Day7.Content> redRules = rules.get("light red");
        assertEquals(2, redRules.size());
        int matchCount = 0;
        for (Day7.Content c : redRules) {
            switch (c.color) {
                case "bright white":
                    assertEquals(1, c.count);
                    matchCount++;
                    break;
                case "muted yellow":
                    assertEquals(2, c.count);
                    matchCount++;
                    break;
                default:
                    break;
            }
        }
        assertEquals(2, matchCount);
    }

    @Test
    public void testSmallParents() throws IOException {
        Path p = Paths.get("small");
        List<String> lines = Files.readAllLines(p);
        Map<String, Day7.Color> rules = day.parseRulesForParents(lines);
        String key = "shiny gold";
        assertTrue(rules.containsKey(key));
        Day7.Color c = rules.get(key);

        Set<String> ancestors=day.ancestorsOf(key,rules);

        assertEquals(4,ancestors.size());
    }

    @Test
    public void testMatcher() {
        Matcher m = day.contentPattern.matcher("1 bright white bag");
        assertTrue(m.matches());
        m = day.contentPattern.matcher("2 muted yellow bags.");
        assertTrue(m.matches());

    }

    @Test
    public void testParseInput() throws IOException {
        Path p = Paths.get("input");
        List<String> lines = Files.readAllLines(p);
        day.verbose = true;
        Map<String, List<Day7.Content>> rules = day.parseRulesForContainment(lines);
    }


    @Test
    public void testSmallPart2() throws IOException {
        Path p = Paths.get("small2");
        List<String> lines = Files.readAllLines(p);
//        day.verbose=true;
        Map<String, List<Day7.Content>> rules = day.parseRulesForContainment(lines);
        assertEquals(7, rules.size());
        assertEquals(126,day.countBags("shiny gold",rules));
    }

}
