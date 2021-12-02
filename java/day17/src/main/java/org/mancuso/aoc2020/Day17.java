package org.mancuso.aoc2020;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Day17 extends AbstractDay {


    @Override
    public String name() {
        return "Day17";
    }

    @Override
    public String part1() {
        String result = null;
        try {
            Path p = Paths.get("day17/input");
            List<String> lines = Files.readAllLines(p);
            Space<Status> space = new Space<>();
            space.init(lines, Status::statusConverter, Status.ACTIVE);
            Map<Key, MapElement<Status>> cycleResult = null;
            for (int i = 0; i < 6; i++) {
                cycleResult = space.part1cycle();
            }
            result = Integer.toString(cycleResult.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public String part2() {
        String result = null;
        try {
            Path p = Paths.get("day17/input");
            List<String> lines = Files.readAllLines(p);
            Space<Status> space = new Space<>();
            space.init(lines, Status::statusConverter, Status.ACTIVE);
            Map<Key4D, MapElement<Status>> cycleResult = null;
            for (int i = 0; i < 6; i++) {
                cycleResult = space.part2cycle();
            }
            result = Integer.toString(cycleResult.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}