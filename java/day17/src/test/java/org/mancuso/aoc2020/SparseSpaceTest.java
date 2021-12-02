package org.mancuso.aoc2020;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SparseSpaceTest {
    @Test
    public void testNeighbors() {
        Key key = new Key(0, 0, 0);
        Set<Key> neighbors = key.neighbors();
        assertEquals(26, neighbors.size());
        List<Key> expectedKeys = new ArrayList<>();
        for (int z = -1; z < 2; z++)
            for (int y = -1; y < 2; y++)
                for (int x = -1; x < 2; x++) {
                    Key newKey = new Key(x, y, z);
                    if (!newKey.equals(key)) {
                        expectedKeys.add(newKey);
                    }
                }
        for (Key thisKey : expectedKeys) {
            assertTrue("Should contain " + thisKey, neighbors.contains(thisKey));
        }
        assertTrue(neighbors.contains(new Key(-1, -1, 0)));
    }

    @Test
    public void testParseMap() {
        List<String> lines = Arrays.asList(new String[]{"...", ".#.", "..."});
        Space<Status> space = new Space<>();
        Map<Key, MapElement<Status>> result = space.parseMap(lines, Status::statusConverter, Status.ACTIVE);
        assertEquals(1, result.size());
        assertEquals(new Key(1, 1, 0), result.keySet().stream().findFirst().get());

        lines = Arrays.asList(new String[]{"..#", ".#.", "#.."});
        result = space.parseMap(lines, Status::statusConverter, Status.ACTIVE);
        assertEquals(3, result.size());
        Key[] expectedKeys = new Key[]{new Key(0, 0, 0), new Key(1, 1, 0), new Key(2, 2, 0)};
        for (Key expectedKey : expectedKeys) {
            assertTrue("should contain " + expectedKey, result.containsKey(expectedKey));
        }

    }

    @Test
    public void testSmall() {
        Space<Status> space = new Space<>();
        try {
            Path p = Paths.get("small");
            List<String> lines = Files.readAllLines(p);
            space.init(lines, Status::statusConverter, Status.ACTIVE);

            // verify parseMap is correct
            Map<Key, MapElement<Status>> map = space.parseMap(lines, Status::statusConverter, Status.ACTIVE);
            assertEquals(5, map.size());
            Key[] expectedKeys = new Key[]{
                    new Key(1, 2, 0),
                    new Key(2, 1, 0),
                    new Key(0, 0, 0), new Key(1, 0, 0), new Key(2, 0, 0)
            };
            for (Key expectedKey : expectedKeys) {
                assertTrue("map should contain " + expectedKey, map.containsKey(expectedKey));
            }

            //verify 1st cycle worked
            var firstCycle = space.part1cycle();
            assertEquals(11, firstCycle.size());
            expectedKeys = new Key[]{
                    new Key(1, -1, 0), new Key(1, 0, 0), new Key(2, 0, 0), new Key(0, 1, 0), new Key(2, 1, 0),
                    new Key(1, -1, -1), new Key(2, 0, -1), new Key(0, 1, -1),
                    new Key(1, -1, 1), new Key(2, 0, 1), new Key(0, 1, 1)
            };
            for (Key expectedKey : expectedKeys) {
                assertTrue("cycle 1 map should contain " + expectedKey, firstCycle.containsKey(expectedKey));
            }
            var secondCycle = space.part1cycle();
            assertEquals(21, secondCycle.size());
            var thirdCycle = space.part1cycle();
            assertEquals(38, thirdCycle.size());
            space.part1cycle();//fourth
            space.part1cycle();//fifth
            var sixthCycle = space.part1cycle();//sixth
            assertEquals(112, sixthCycle.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testCalculateNeighbors() {
        Space<Status> space = new Space<>();
        try {
            Path p = Paths.get("small");
            List<String> lines = Files.readAllLines(p);
            Map<Key, MapElement<Status>> map = space.parseMap(lines, Status::statusConverter, Status.ACTIVE);
            Map<Key, Integer> neighbors = space.calculateNeighborCount(map);
            Key key1 = new Key(-1, -1, 0);
            assertTrue("should contain [-1,-1,0]", neighbors.containsKey(key1));
            assertEquals(1, (int) neighbors.get(key1));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testCalculate4dNeighbors() {
        Key4D key = new Key4D(0, 0, 0, 0);
        var keyset = key.neighbors();
        assertEquals(80, keyset.size());
        Space<Status> space = new Space<>();
        try {
            Path p = Paths.get("small");
            List<String> lines = Files.readAllLines(p);
            Map<Key4D, MapElement<Status>> map = space.convertTo4D(space.parseMap(lines, Status::statusConverter, Status.ACTIVE));
            Map<Key4D, Integer> neighbors = space.calculate4DNeighborCount(map);
            Key4D key1 = new Key4D(-1, -1, 0, 0);
            assertTrue("should contain [-1,-1,0,0]", neighbors.containsKey(key1));
            assertEquals(1, (int) neighbors.get(key1));
            Key4D key0 = new Key4D(-1, -1, 0, -1);
            assertTrue("should contain [-1,-1,0,-1]", neighbors.containsKey(key1));
            assertEquals(1, (int) neighbors.get(key0));
            Key4D key2 = new Key4D(-1, -1, 0, 1);
            assertTrue("should contain [-1,-1,0,1]", neighbors.containsKey(key2));
            assertEquals(1, (int) neighbors.get(key0));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private List<Key4D> buildCycle1Expected4DKeys() {

        Key4D[] allKeys = new Key4D[]{
                //z=-1,w=-1
                new Key4D(1, -1, -1, -1), new Key4D(2, 0, -1, -1), new Key4D(0, 1, -1, -1),
                //z=0,w=-1
                new Key4D(1, -1, 0, -1), new Key4D(2, 0, 0, -1), new Key4D(0, 1, 0, -1),
                //z=1,w=-1
                new Key4D(1, -1, 1, -1), new Key4D(2, 0, 1, -1), new Key4D(0, 1, 1, -1),

                //z=-1,w=0
                new Key4D(1, -1, -1, 0), new Key4D(2, 0, -1, 0), new Key4D(0, 1, -1, 0),
                //z=0,w=0
                new Key4D(1, -1, 0, 0), new Key4D(2, 0, 0, 0), new Key4D(0, 1, 0, 0)
                , new Key4D(2, 1, 0, 0), new Key4D(1, 0, 0, 0),
                //z=1,w=0
                new Key4D(1, -1, 1, 0), new Key4D(2, 0, 1, 0), new Key4D(0, 1, 1, 0),

                //z=-1,w=1
                new Key4D(1, -1, -1, 1), new Key4D(2, 0, -1, 1), new Key4D(0, 1, -1, 1),
                //z=0,w=1
                new Key4D(1, -1, 0, 1), new Key4D(2, 0, 0, 1), new Key4D(0, 1, 0, 1),
                //z=1,w=1
                new Key4D(1, -1, 1, 1), new Key4D(2, 0, 1, 1), new Key4D(0, 1, 1, 1),
        };
        return Arrays.asList(allKeys);
    }

    @Test
    public void testSmallPart2() {
        Space<Status> space = new Space<>();
        try {
            Path p = Paths.get("small");
            List<String> lines = Files.readAllLines(p);
            space.init(lines, Status::statusConverter, Status.ACTIVE);

            var firstCycle = space.part2cycle();
            List<Key4D> expectedKeys = buildCycle1Expected4DKeys();
            assertEquals(29,expectedKeys.size());//check our test
            for (Key4D expectedKey : expectedKeys) {
                assertTrue("should have " + expectedKey, firstCycle.containsKey(expectedKey));
            }
            space.part2cycle();//second
            space.part2cycle();//third
            space.part2cycle();//fourth
            space.part2cycle();//fifth
            var sixthCycle = space.part2cycle();//sixth
            assertEquals(848, sixthCycle.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
