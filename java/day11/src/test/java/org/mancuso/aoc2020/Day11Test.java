package org.mancuso.aoc2020;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mancuso.aoc2020.Square.*;


public class Day11Test {
    Day11 day = new Day11();


    @Test
    public void testSmall() throws IOException {
        Path p = Paths.get("small");
        List<String> lines = Files.readAllLines(p);
        SeatMap map = day.buildMap(lines);
        assertEquals(Empty, map.squareAt(0, 0));
        assertEquals(Floor, map.squareAt(0, 1));

//        SeatMap.Walker printWalker = map::printer;
//        map.apply(printWalker);

        assertEquals(3, map.adjacentSquares(0, 0).size());
        assertEquals(8, map.adjacentSquares(1, 1).size());
        int applyCount = 0;
        do {
            day.println("Iteration " + applyCount++);
            map = day.part1Walker(map);
        } while (map.applyChanged && applyCount < 7);
        assertEquals(6, applyCount);
        assertEquals(37, map.filledSeats());
    }

    @Test
    public void testAdjacent() throws Exception {
        Path p = Paths.get("small");
        List<String> lines = Files.readAllLines(p);
        SeatMap map = day.buildMap(lines);
        List<Square> adjSquares = map.adjacentSquares(1, 0);
        assertEquals("Should be 5 adjacent squares", 5, adjSquares.size());
        assertEquals("Should be 3 empty squares", 3, adjSquares.stream().filter(x -> x == Empty).count());
        assertEquals("Should be 2 floor squares", 2, adjSquares.stream().filter(x -> x == Floor).count());

    }


    @Test
    public void testSightLine() throws Exception {
        Path p = Paths.get("eightSeats");
        List<String> lines = Files.readAllLines(p);
        SeatMap map = day.buildMap(lines);
        assertEquals(Filled, map.raySeat(SeatMap.nw, 4, 3));
        assertEquals(Filled, map.raySeat(SeatMap.n, 4, 3));
        assertEquals(Filled, map.raySeat(SeatMap.ne, 4, 3));
        assertEquals(Filled, map.raySeat(SeatMap.w, 4, 3));
        assertEquals(Filled, map.raySeat(SeatMap.e, 4, 3));
        assertEquals(Filled, map.raySeat(SeatMap.sw, 4, 3));
        assertEquals(Filled, map.raySeat(SeatMap.s, 4, 3));
        assertEquals(Filled, map.raySeat(SeatMap.se, 4, 3));

        assertEquals(Floor, map.raySeat(SeatMap.n, 0, 7));

        p = Paths.get("oneSeat");
        lines = Files.readAllLines(p);
        map = day.buildMap(lines);
        assertEquals(Empty, map.raySeat(SeatMap.e, 1, 1));
        assertEquals(Floor, map.raySeat(SeatMap.w, 1, 1));
        assertEquals(Floor, map.raySeat(SeatMap.ne, 0, 0));

        p = Paths.get("noSeats");
        lines = Files.readAllLines(p);
        map = day.buildMap(lines);
        assertEquals(Floor, map.raySeat(SeatMap.nw, 3, 3));
        assertEquals(Floor, map.raySeat(SeatMap.n, 3, 3));
        assertEquals(Floor, map.raySeat(SeatMap.ne, 3, 3));
        assertEquals(Floor, map.raySeat(SeatMap.w, 3, 3));
        assertEquals(Floor, map.raySeat(SeatMap.e, 3, 3));
        assertEquals(Floor, map.raySeat(SeatMap.sw, 3, 3));
        assertEquals(Floor, map.raySeat(SeatMap.s, 3, 3));
        assertEquals(Floor, map.raySeat(SeatMap.se, 3, 3));
    }

    @Test
    public void testSmallPart2() throws Exception {
        Path p = Paths.get("small");
        List<String> lines = Files.readAllLines(p);
        SeatMap map = day.buildMap(lines);
        assertEquals(Empty, map.squareAt(0, 0));
        assertEquals(Floor, map.squareAt(0, 1));

        assertEquals(3, map.adjacentSquares(0, 0).size());
        assertEquals(8, map.adjacentSquares(1, 1).size());
        int applyCount = 0;
        do {
            day.println("Iteration " + applyCount++);
            map = day.part2Walker(map);
        } while (map.applyChanged && applyCount < 8);
        assertEquals(7, applyCount);
        assertEquals(26, map.filledSeats());
    }
}
