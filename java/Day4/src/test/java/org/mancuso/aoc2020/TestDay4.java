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

public class TestDay4 {
    @Test public void testSmall() throws IOException {
        Path p = Paths.get("small");
        List<String> lines=Files.readAllLines(p);
        Day4 d4 = new Day4();
        int valid= d4.process(lines,false);
        assertEquals(2,valid);
    }
    @Test public void testSmallDay2() throws IOException {
        Path p = Paths.get("small");
        List<String> lines=Files.readAllLines(p);
        Day4 d4 = new Day4();
        int valid= d4.process(lines,true);
        assertEquals(2,valid);
    }

}
