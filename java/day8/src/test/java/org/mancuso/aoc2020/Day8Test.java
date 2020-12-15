package org.mancuso.aoc2020;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day8Test {
    Day8 day = new Day8();
    @Test
    public void testSmall() throws IOException {
        Path p = Paths.get("small");
        List<String> lines = Files.readAllLines(p);
        Part1Context ctx = new Part1Context();
        day.run(lines,ctx);
        assertEquals(5,ctx.acc);
    }


    @Test
    public void testSmallPart2() throws IOException {
        Path p = Paths.get("small");
        List<String> lines = Files.readAllLines(p);
        Part2Context ctx = new Part2Context();
        int result=day.runPart2(lines,ctx);
        assertEquals(8,result);
    }
}
