package org.mancuso.aoc2020;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    public void printDeltas(List<Long> input) {
        Long lastJoltage = 0L;
        int consecutive1s=0;
        int lastDelta=0;
        for (int i=0;i<input.size();i++) {
            Long curJoltage=input.get(i);
            int delta = (int) (curJoltage - lastJoltage);
            if(delta==3&&lastDelta==1) {
                printf("[%d]",consecutive1s);
            }
            if(delta!=lastDelta){
                println("");
            }
            lastDelta=delta;
            printf("%d ",delta);
            if(delta==3) {
                consecutive1s=0;
            } else {
                consecutive1s++;
            }
            lastJoltage = curJoltage;
        }
    }

    public int calcPathsFrom1s(int consecutive1s){
        System.out.println(consecutive1s);
        switch (consecutive1s) {
            case 5:
                return 13;
            case 4:
                return 6;
            case 3:
                return 3;
            case 2:
                return 2;
            case 1:
                return 1;
            default:
                throw new RuntimeException("Un-calculated case: "+consecutive1s);
        }
    }

    public long countPaths(List<Long> input) {
        List<Integer> paths = new ArrayList<>();
        Long lastJoltage = 0L;
        int consecutive1s=0;
        int lastDelta=0;
        for (int i=0;i<input.size();i++) {
            Long curJoltage=input.get(i);
            int delta = (int) (curJoltage - lastJoltage);
            if(delta==3&&lastDelta==1) {
                printf("[%d]",consecutive1s);
                paths.add(calcPathsFrom1s(consecutive1s));
            }
            if(delta!=lastDelta){
                println("");
            }
            lastDelta=delta;
            printf("%d ",delta);
            if(delta==3) {
                consecutive1s=0;
            } else {
                consecutive1s++;
            }
            lastJoltage = curJoltage;
        }
        long result=1L;
        for(int i=0;i<paths.size();i++){
            result=result*paths.get(i);
            if(result<1) throw new RuntimeException(("Overflow; larger datatype needed"));
        }
        return result;
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
