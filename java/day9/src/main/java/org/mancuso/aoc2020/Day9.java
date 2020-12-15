package org.mancuso.aoc2020;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Day9 extends AbstractDay {
    @Override
    public String name() {
        return "Day9";
    }

    @Override
    public String part1() {
        Path p = Paths.get("day9/input");
        String result=null;
        try {
            List<String> lines = Files.readAllLines(p);
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public String part2() {
        Path p = Paths.get("day9/input");
        String result=null;
        try {
            List<String> lines = Files.readAllLines(p);
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    boolean isValid(Integer value,LinkedList<Integer> buffer) {
        return false;
    }

    public int findOutlier(List<String> lines, int preambleSize) {
        LinkedList<Integer> buffer = new LinkedList<>();
        List<Integer> input = lines.stream().map(x->Integer.parseInt(x)).collect(Collectors.toList());
        for(int i=0;i<preambleSize;i++) {
            buffer.add(input.get(i));
        }
        int idx=preambleSize;
        while(isValid(input.get(idx),buffer)) {

        }
        return 0;
    }
}
