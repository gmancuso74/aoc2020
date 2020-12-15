package org.mancuso.aoc2020;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

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
            result.add(Long.valueOf((long) in));
        }
        return result;
    }

    @Test
    public void testIsValid() {
        LinkedList<Long> buffer = new LinkedList<>();
        buffer.addAll(buildList(35, 20, 15, 25, 47));
        LinkedList<Long> input = buildList(40, 62, 55, 65, 95, 102, 117, 150, 182, 127, 219, 299, 277, 309, 576);
        boolean foundOutlier = false;
        for (Long value : input) {
            if (value.longValue() == 127) {
                foundOutlier = true;
                assertFalse(day.isValid(value, buffer));
                break;
            } else {
                foundOutlier = false;
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
        List<String> lines = Files.readAllLines(p);
        Long outlier = day.findOutlier(lines, 5);
        assertTrue(Long.valueOf((long) 127).compareTo(outlier) == 0);
    }
}
