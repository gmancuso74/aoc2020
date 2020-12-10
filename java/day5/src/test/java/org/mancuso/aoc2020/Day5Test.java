package org.mancuso.aoc2020;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class Day5Test {


    @Test
    public void testSmall() throws IOException {
        String input = "FBFBBFFRLR";
        Day5 d5 = new Day5();
        assertEquals(44, d5.getRow(input));
        assertEquals(5, d5.getSeat(input));
        assertEquals(357,d5.getSeatID(input));

        assertEquals(567,d5.getSeatID("BFFFBBFRRR"));
        assertEquals(119,d5.getSeatID("FFFBBBFRRR"));
        assertEquals(820,d5.getSeatID("BBFFBBFRLL"));

    }

    @Test
    public void testSmallPart2() throws IOException {
        Path p = Paths.get("small");
        List<String> lines = Files.readAllLines(p);
        int highest=new Day5().findHighest(lines);
        assertEquals(820,highest);
    }

}
