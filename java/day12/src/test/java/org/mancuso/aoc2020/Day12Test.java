package org.mancuso.aoc2020;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mancuso.aoc2020.Facing.*;

public class Day12Test {
    Day12 day = new Day12();

    @Test
    public void testSmall() throws Exception {
        Path p = Paths.get("small");
        List<String> lines = Files.readAllLines(p);
        Command curCmd;
        Ferry ferry = new Ferry();
        for (String line : lines) {
            System.out.println(line);
            curCmd = new Command(line);
            ferry.Apply(curCmd);
            System.out.println(ferry.status());
        }
        assertEquals(25, day.mDist(ferry.position));
    }

    @Test
    public void testFacing() {
        assertTrue(Facing.from(180).isPresent());
        assertEquals(South, Facing.from(180).get());

        assertTrue(Facing.from(0).isPresent());
        assertEquals(North, Facing.from(0).get());

        assertTrue(Facing.from(90).isPresent());
        assertEquals(East, Facing.from(90).get());

        assertTrue(Facing.from(270).isPresent());
        assertEquals(West, Facing.from(270).get());
    }

    @Test
    public void testRotate() {
        Day2Ferry ferry = new Day2Ferry();
        ferry.waypoint.x = 10;
        ferry.waypoint.y = 4;
        ferry.Rotate(90);
        assertEquals(4, ferry.waypoint.x);
        assertEquals(-10, ferry.waypoint.y);

        ferry.Rotate(-90);
        assertEquals(10, ferry.waypoint.x);
        assertEquals(4, ferry.waypoint.y);
    }

    @Test
    public void testDay2Small() throws Exception {
        Path p = Paths.get("small");
        List<String> lines = Files.readAllLines(p);
        Command curCmd;
        Day2Ferry ferry = new Day2Ferry();
        for (String line : lines) {
            System.out.println(line);
            curCmd = new Command(line);
            ferry.Apply(curCmd);
        }
        assertEquals(286, day.mDist(ferry.position));
    }
}
