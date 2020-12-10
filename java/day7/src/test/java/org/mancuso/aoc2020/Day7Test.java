package org.mancuso.aoc2020;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class Day7Test {

    Day7 day = new Day7();

    @Test
    public void testSmall() throws IOException {
        Path p = Paths.get("small");
        List<String> lines = Files.readAllLines(p);

    }

    @Test
    public void testSmallPart2() throws IOException {
        Path p = Paths.get("small");
    }

}
