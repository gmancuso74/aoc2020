package org.mancuso.aoc2020;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Day13Test {

    public static final int part2SmallAnswer = 1068781;
    Day13 day = new Day13();

    @Test
    public void testSmall() throws Exception {
        Path p = Paths.get("small");
        List<String> lines = Files.readAllLines(p);
        assertEquals(2, lines.size());
        day.Initialize(lines);
        day.doPart1();
        assertEquals(944, day.departureTime);
        assertEquals(59, day.departingBus.intValue());
    }

    @Test
    public void testFastSolve() throws Exception {
        Path p = Paths.get("small");
        List<String> lines = Files.readAllLines(p);
        assertEquals(2, lines.size());
        day.Initialize(lines);
    }

    @Test
    public void testSmallPart2() throws Exception {
        Path p = Paths.get("small");
        List<String> lines = Files.readAllLines(p);
        assertEquals(2, lines.size());
        day.Initialize(lines);
        assertTrue(day.validate(part2SmallAnswer, day.busRecs));
        assertEquals(part2SmallAnswer, day.departureTime);
    }
}
