package org.mancuso.aoc2020;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day12 extends AbstractDay {
    @Override
    public String name() {
        return "Day12";
    }


    @Override
    public String part1() {
        Path p = Paths.get("day12/input");
        String result = null;
        try {
            List<String> lines = Files.readAllLines(p);
            Command curCmd;
            Ferry ferry = new Ferry();
            for (String line : lines) {
                curCmd = new Command(line);
                ferry.Apply(curCmd);
            }
            result=Integer.toString(mDist(ferry.position));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    public String part2() {
        Path p = Paths.get("day12/input");
        String result = null;
        try {
            List<String> lines = Files.readAllLines(p);
            Command curCmd;
            Day2Ferry ferry = new Day2Ferry();
            for (String line : lines) {
                curCmd = new Command(line);
                ferry.Apply(curCmd);
            }
            result=Integer.toString(mDist(ferry.position));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public int mDist(Point position) {
        return Math.abs(position.x) + Math.abs(position.y);
    }
}
