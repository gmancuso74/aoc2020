package org.mancuso.aoc2020;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day9Test {
    public Day9 day = new Day9();

    @Test
    public void testName() {
        assertEquals("Day9", new Day9().name());
    }

    @Test
    public void testSmall() throws IOException {
        Path p = Paths.get("small");
        List<String> lines = Files.readAllLines(p);
        int outlier=day.findOutlier(lines,5);
        assertEquals(127,outlier);
    }
}
