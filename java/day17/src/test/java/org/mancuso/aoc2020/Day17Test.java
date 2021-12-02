package org.mancuso.aoc2020;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day17Test {


    public String[] buildEmpty(int size) {
        String[] result = new String[size];
        String row = new String(new char[size]).replace("\0", ".");
        for (int i = 0; i < size; i++) {
            result[i] = row;
        }
        return result;
    }

    @Test
    public void testInit() {
        String[] lines = buildEmpty(10);
        OldSpace<Status> space = new OldSpace<>(Status.INACTIVE);
        space.init(lines, Status::statusConverter);
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                assertEquals(Status.INACTIVE, space.get(x, y, 0));
            }
        }
    }

    @Test
    public void testMapElementPrint() {
        MapElement<Status> statElement = new MapElement<>();
        statElement.set(Status.INACTIVE);
        assertEquals(".", statElement.print());
        MapElement<Integer> intElement = new MapElement<>();
        intElement.set(23);
        assertEquals("23", intElement.print());
    }

    @Test
    public void testMapPrint() {
        OldSpace<Status> space = new OldSpace<>(Status.INACTIVE);
        String[] lines = buildEmpty(4);
        space.init(lines, Status::statusConverter);
        String n = System.lineSeparator();
        String plane = "...." + n + "...." + n + "...." + n + "....";
        String expected = "Level 0" + n + plane + n;
        assertEquals(expected, space.print());

        lines = buildEmpty(2);
        space = new OldSpace<>(Status.INACTIVE);
        space.init(lines, Status::statusConverter);

        space.set(0, 1, 1, Status.ACTIVE);
        space.set(1, 0, 1, Status.ACTIVE);
        space.set(1, 1, 0, Status.ACTIVE);
        space.set(0, 0, 0, Status.ACTIVE);
        String lvl1 = "Level 1" + n + "#." + n + ".#" + n;
        String lvl0 = "Level 0" + n + ".#" + n + "#." + n;
        expected = lvl1 + lvl0;
        assertEquals(expected, space.print());
        System.out.println(expected);
    }

    @Test
    public void testSmall() {
        try {
            Path p = Paths.get("small");
            List<String> lines = Files.readAllLines(p);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
