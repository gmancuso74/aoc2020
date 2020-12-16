package org.mancuso.aoc2020;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day10 extends AbstractDay {

    public Map<Integer, Integer> countDeltas(List<Long> input) {
        Map<Integer, Integer> deltaCounts = new HashMap<>();
        Long lastJoltage = 0L;
        for (Long curJoltage : input) {
            int delta = (int) (curJoltage - lastJoltage);
            apply(deltaCounts, delta);
            lastJoltage = curJoltage;
        }
        apply(deltaCounts, 3);//the device is always 3 more than max
        return deltaCounts;
    }

    public void apply(Map<Integer, Integer> deltaCounts, int delta) {
        if (delta > 3) {
            throw new RuntimeException((String.format("Delta too high (%d)", delta)));
        }
        int curCount = 0;
        if (deltaCounts.containsKey(delta)) {
            curCount = deltaCounts.get(delta);
        }
        deltaCounts.put(delta, curCount + 1);
    }

    @Override
    public String name() {
        return "Day10";
    }

    @Override
    public String part1() {
        String result = null;
        try {
            Path p = Paths.get("day10/input");
            List<Long> input = Files.readAllLines(p).stream().map(Long::parseLong).sorted(Long::compare).collect(Collectors.toList());
            Map<Integer, Integer> deltaCounts = countDeltas(input);
            int d1 = deltaCounts.get(1);
            int d3 = deltaCounts.get(3);
            long product = (long) d1 * d3;
            result = String.valueOf(product);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public String part2() {
        return null;
    }
}
