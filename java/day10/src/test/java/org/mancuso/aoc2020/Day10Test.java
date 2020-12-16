package org.mancuso.aoc2020;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class Day10Test {
    Day10 day = new Day10();


    @Test
    public void testSmallPart1() throws Exception {
        Path p = Paths.get("small");
        List<Long> input = Files.readAllLines(p).stream().map(Long::parseLong).sorted(Long::compare).collect(Collectors.toList());
        Map<Integer, Integer> deltaCounts = day.countDeltas(input);
        assertEquals(7, deltaCounts.get(1).intValue());
        assertEquals(5, deltaCounts.get(3).intValue());
    }

    @Test
    public void testSmall2Part1() throws Exception {
        Path p = Paths.get("small2");
        List<Long> input = Files.readAllLines(p).stream().map(Long::parseLong).sorted(Long::compare).collect(Collectors.toList());
        Map<Integer, Integer> deltaCounts = day.countDeltas(input);
        assertEquals(22, deltaCounts.get(1).intValue());
        assertEquals(10, deltaCounts.get(3).intValue());
    }
}
