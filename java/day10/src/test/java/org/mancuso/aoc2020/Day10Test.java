package org.mancuso.aoc2020;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    @Test
    public void testSmallPart2() throws Exception {
        Path p = Paths.get("small");
        List<Long> input = Files.readAllLines(p).stream().map(Long::parseLong).sorted(Long::compare).collect(Collectors.toList());
        input.add(0, 0L);
        input.add(input.get(input.size() - 1) + 3);
        assertEquals(19208,day.countPaths(input));

    }
    List<String>
    consecutiveRanges(int[] a)
    {
        int length = 1;
        List<String> list
                = new ArrayList<String>();

        // If the array is empty,
        // return the list
        if (a.length == 0) {
            return list;
        }

        // Traverse the array from first position
        for (int i = 1; i <= a.length; i++) {

            // Check the difference between the
            // current and the previous elements
            // If the difference doesn't equal to 1
            // just increment the length variable.
            if (i == a.length
                    || a[i] - a[i - 1] != 1) {

                // If the range contains
                // only one element.
                // add it into the list.
                if (length == 1) {
                    list.add(
                            String.valueOf(a[i - length]));
                }
                else {

                    // Build the range between the first
                    // element of the range and the
                    // current previous element as the
                    // last range.
                    list.add(a[i - length]
                            + " -> " + a[i - 1]);
                }

                // After finding the first range
                // initialize the length by 1 to
                // build the next range.
                length = 1;
            }
            else {
                length++;
            }
        }

        return list;
    }
}
