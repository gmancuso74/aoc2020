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
            long outlier=findOutlier(lines,25);
            result=String.valueOf(outlier);
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

    boolean isValid(Long value,LinkedList<Long> buffer) {
        CombinationBuilder<Long> cBuilder = new CombinationBuilder<>();
        List<NTuple<Long>> combos = cBuilder.getCombinations(buffer,2);
        return combos.stream().anyMatch(x->value==x.get(0)+x.get(1));
    }

    public Long findOutlier(List<String> lines, int preambleSize) {
        LinkedList<Long> buffer = new LinkedList<>();
        List<Long> input = lines.stream().map(x->Long.parseLong(x)).collect(Collectors.toList());
        for(int i=0;i<preambleSize;i++) {
            buffer.add(input.get(i));
        }
        Long outlier=null;
        int idx=preambleSize;
        while(idx<input.size()) {
            Long value=input.get(idx++);
            if(!isValid(value,buffer)) {
                outlier=value;
                return outlier;
            } else {
                buffer.removeFirst();
                buffer.add(value);
            }
        }
        return null;
    }
}
