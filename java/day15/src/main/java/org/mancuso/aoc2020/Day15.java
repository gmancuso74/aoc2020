package org.mancuso.aoc2020;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day15 extends AbstractDay {
    Map<Long, Long> memory = new HashMap<Long, Long>();
    Long spoken;
    Long turn;

    @Override
    public String name() {
        return "Day15";
    }

    @Override
    public String part1() {
        String result=null;
        try {
            Path p = Paths.get("day15/input");
            List<String> lines = Files.readAllLines(p);
            List<Long> numbers = Arrays.stream(lines.get(0).split(",")).map(Long::parseLong).collect(Collectors.toList());
            part1Init(numbers);
            Long resultNum=part1Run(2020);
            result=resultNum.toString();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public String part2() {
        String result=null;
        try {
            Path p = Paths.get("day15/input");
            List<String> lines = Files.readAllLines(p);
            List<Long> numbers = Arrays.stream(lines.get(0).split(",")).map(Long::parseLong).collect(Collectors.toList());
            part1Init(numbers);
            Long resultNum=part1Run(30000000);
            result=resultNum.toString();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


    public void part1Init(List<Long> input) {
        memory = new HashMap<>();
        turn = 1L;
        for (int i = 0; i < input.size(); i++) {
            spoken = input.get(i);
            if (turn < input.size()) {
                memory.put(spoken,turn++);
            }
        }
        turn++;
    }

    public long part1Run(long finalTurn) {
        while (turn <= finalTurn) {
            Long prevSpoken = spoken;
            Long prevTurn = turn - 1;
            if (memory.containsKey(spoken)) {
                spoken = prevTurn - memory.get(spoken);
            } else {
                spoken = 0L;
            }
            memory.put(prevSpoken, prevTurn);
            turn++;
        }
        return spoken;
    }
}
