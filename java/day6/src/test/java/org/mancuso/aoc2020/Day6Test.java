package org.mancuso.aoc2020;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class Day6Test {

    Day6 d6 = new Day6();

    @Test
    public void testSmall() throws IOException {
        Path p = Paths.get("small");
        List<String> lines = Files.readAllLines(p);
        List<Set<Character>> groups = d6.load(lines);
        assertEquals(5,groups.size());
        assertEquals(3,groups.get(0).size());
        assertEquals(3,groups.get(1).size());
        assertEquals(3,groups.get(2).size());
        assertEquals(1,groups.get(3).size());
        assertEquals(1,groups.get(4).size());
        assertEquals(11,d6.sum(groups));
    }

    @Test
    public void testSmallPart2() throws IOException {
        Path p = Paths.get("small");
        List<String> lines = Files.readAllLines(p);
        List<Set<Character>> groups = d6.loadPart2(lines);
        assertEquals(5,groups.size());
        assertEquals(3,groups.get(0).size());
        assertEquals(0,groups.get(1).size());
        assertEquals(1,groups.get(2).size());
        assertEquals(1,groups.get(3).size());
        assertEquals(1,groups.get(4).size());
        assertEquals(6,d6.sum(groups));

    }

}
