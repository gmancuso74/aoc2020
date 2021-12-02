package org.mancuso.aoc2020;

import org.junit.Test;
import static org.junit.Assert.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day16Test {
    Day16 day = new Day16();

    @Test
    public void testRanges() {
        List<Range> ranges = day.parseRanges("1-3 or 5-7");
        assertTrue(Range.isIn(ranges, new Range(1, 3)));
        assertTrue(Range.isIn(ranges, 1));
        assertTrue(Range.isIn(ranges, 2));
        assertTrue(Range.isIn(ranges, 3));
        assertTrue(Range.isIn(ranges, new Range(5, 7)));
        assertTrue(Range.isIn(ranges, 5));
        assertTrue(Range.isIn(ranges, 6));
        assertTrue(Range.isIn(ranges, 7));

        assertFalse(Range.isIn(ranges, 4));
        assertFalse(Range.isIn(ranges, 0));
        assertFalse(Range.isIn(ranges, 8));
    }

    @Test
    public void testRangeOverlaps() {
        Range r1 = new Range(1, 3);
        Range r2 = new Range(2, 4);
        assertTrue(r1.overlaps(r2));
        assertTrue(r2.overlaps(r1));
    }

    @Test
    public void testRangeConsolidate() {
        List<Range> ranges = new ArrayList<>();
        ranges.add(new Range(1, 3));
        ranges.add(new Range(5, 7));
        ranges.add(new Range(6, 11));
        ranges.add(new Range(13, 40));
        ranges.add(new Range(45, 50));

        List<Range> result = Range.consolidate(ranges);
        assertEquals(4, result.size());
        assertTrue(Range.isIn(result, new Range(1, 3)));
        assertTrue(Range.isIn(result, new Range(5, 11)));
        assertTrue(Range.isIn(result, new Range(13, 40)));
        assertTrue(Range.isIn(result, new Range(45, 50)));

        assertFalse(Range.isIn(result, new Range(5, 7)));
        assertFalse(Range.isIn(result, 4));
        assertFalse(Range.isIn(result, 41));

        ranges.add(new Range(2,10));
        result=Range.consolidate(ranges);
        assertEquals(3,result.size());
        assertTrue(Range.isIn(result,new Range(1,11)));
        assertTrue(Range.isIn(result,new Range(13,40)));
        assertTrue(Range.isIn(result,new Range(45,50)));

    }

    @Test
    public void testSmall() throws Exception {
        Path p = Paths.get("small");
        List<String> lines = Files.readAllLines(p);
        Input input = day.parseInput(lines);
        assertArrayEquals(new Integer[]{7, 1, 14}, input.myTicket.toArray());
        assertEquals(4, input.nearbyTickets.size());
        assertEquals(3, input.rules.size());

        List<Integer> invalids = day.listInvalidNumbers(input);
        assertEquals(3, invalids.size());
        assertTrue(invalids.contains(4));
        assertTrue(invalids.contains(55));
        assertTrue(invalids.contains(12));
        Integer result = invalids.stream().reduce(Integer::sum).get();
        assertEquals(71, (int) result);
    }

    @Test
    public void testSmallPart2() throws Exception {
        Path p = Paths.get("small");
        List<String> lines = Files.readAllLines(p);
        Input input = day.parseInput(lines);
        List<List<Integer>> valids = day.getValidTickets(input);
        assertEquals(1, valids.size());
        input.nearbyTickets = valids;
        Map<String, Integer> map = day.mapFields(input);
        assertEquals(3, map.size());
        assertEquals(0, map.get("row").intValue());
        assertEquals(1, map.get("class").intValue());
        assertEquals(2, map.get("seat").intValue());

        assertEquals(1l, day.runPart2(input));
    }

    @Test
    public void testInputPart2() throws Exception {
        Path p = Paths.get("input");
        List<String> lines = Files.readAllLines(p);
        Input input = day.parseInput(lines);
        List<List<Integer>> valids = day.getValidTickets(input);
        input.nearbyTickets = valids;
//        day.verbose = true;
        var result = day.getPotentialFieldMatches(input);
        assertEquals(20, result.size());
        for (String key : result.keySet()) {
            var potentialMatch = result.get(key);
            System.out.println(key + "\t" + result.get(key).size());
            System.out.println("\t"+String.join(",",potentialMatch.stream().map(x->x.toString()).collect(Collectors.toList())));
        }
//        assertTrue(day.runPart2(input)>294202813);
    }
}
