package org.mancuso.aoc2020;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class Day9Test {
    public Day9 day = new Day9();

    @Test
    public void testName() {
        assertEquals("Day9", new Day9().name());
    }

    public LinkedList<Long> buildList(Integer... input) {
        LinkedList<Long> result = new LinkedList<>();
        for (Integer in : input) {
            result.add((long) in);
        }
        return result;
    }

    @Test
    public void testIsValid() {
        LinkedList<Long> buffer = buildList(35, 20, 15, 25, 47);
        LinkedList<Long> input = buildList(40, 62, 55, 65, 95, 102, 117, 150, 182, 127, 219, 299, 277, 309, 576);
        boolean foundOutlier = false;
        for (Long value : input) {
            if (value == 127) {
                foundOutlier = true;
                assertFalse(day.isValid(value, buffer));
                break;
            } else {
                assertTrue(day.isValid(value, buffer));
                buffer.removeFirst();
                buffer.add(value);
            }
        }
        assertTrue(foundOutlier);
    }

    @Test
    public void testSmall() throws IOException {
        Path p = Paths.get("small");
        List<Long> input = Files.readAllLines(p).stream().map(Long::parseLong).collect(Collectors.toList());
        Long outlier = day.findOutlier(input, 5);
        assertEquals(127L, outlier.longValue());
    }

    @Test
    public void testPart2() throws Exception {
        LinkedList<Long> test = buildList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertEquals(9L, day.sumRange(test, 2, 4));
        assertEquals(17L, day.sumRange(test, 8, 9));
        LinkedList<Long> test2 = buildList(1, 3, 5, 7, 9, 11, 13, 15, 17, 19);
        assertEquals(21L, day.sumRange(test2, 2, 4));
        assertEquals(36L, day.sumRange(test2, 8, 9));
        Path p = Paths.get("small");
        List<Long> input = Files.readAllLines(p).stream().map(Long::parseLong).collect(Collectors.toList());
        Long outlier = day.findOutlier(input, 5);
        day.verbose=true;
        Long part2=day.findPart2(input,outlier);
        assertEquals(62L,part2.longValue());
    }
}
