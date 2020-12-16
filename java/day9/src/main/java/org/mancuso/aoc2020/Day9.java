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
        String result = null;
        try {
            List<Long> input = Files.readAllLines(p).stream().map(Long::parseLong).collect(Collectors.toList());
            long outlier = findOutlier(input, 25);
            result = String.valueOf(outlier);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public String part2() {
        Path p = Paths.get("day9/input");
        String result = null;
        try {
            List<String> lines = Files.readAllLines(p);
            List<Long> input = lines.stream().map(Long::parseLong).collect(Collectors.toList());
            Long outlier = findOutlier(input, 25);
            result = String.valueOf(findPart2(input, outlier));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    NTuple<Long> findMinMax(List<Long> input, int startIdx, int rangeSize) {
        NTuple<Long> result = new NTuple<>(2);
        result.set(0, Long.MAX_VALUE);
        result.set(1, Long.MIN_VALUE);
        for (Long value : input.subList(startIdx, startIdx + rangeSize)) {
            if (value > result.get(1)) result.set(1, value);
            if (value < result.get(0)) result.set(0, value);
        }
        return result;
    }

    public Long findPart2(List<Long> input, Long outlier) {
        long result = 0L;
        for (int i = 0; i < input.size(); i++) {
            int rangeSize = checkRange(input, i, outlier);
            if (rangeSize != -1) {
                NTuple<Long> minMax = findMinMax(input, i, rangeSize);
                result = minMax.get(0) + minMax.get(1);
                break;
            }
        }
        return result;
    }

    boolean isValid(Long value, LinkedList<Long> buffer) {
        CombinationBuilder<Long> cBuilder = new CombinationBuilder<>();
        List<NTuple<Long>> combos = cBuilder.getCombinations(buffer, 2);
        return combos.stream().anyMatch(x -> value == x.get(0) + x.get(1));
    }

    public Long findOutlier(List<Long> input, int preambleSize) {
        LinkedList<Long> buffer = new LinkedList<>();
        for (int i = 0; i < preambleSize; i++) {
            buffer.add(input.get(i));
        }
        Long outlier;
        int idx = preambleSize;
        while (idx < input.size()) {
            Long value = input.get(idx++);
            if (!isValid(value, buffer)) {
                outlier = value;
                return outlier;
            } else {
                buffer.removeFirst();
                buffer.add(value);
            }
        }
        return null;
    }

    public long sumRange(List<Long> input, int from, int to) {
        return input.subList(from, to + 1).stream().mapToLong(Long::longValue).sum();
    }

    public int checkRange(List<Long> input, int start, long target) {
        printf("Checking range starting at idx [%d]\n", start);
        int rangeSize = 1;
        long curSum = 0;
        while (curSum < target && rangeSize + start < input.size()) {
            curSum = sumRange(input, start, start + rangeSize++);
            if (curSum == target) {
                printf("\trange size %d -> !! Match !!\n", rangeSize);
                return rangeSize;
            }
        }
        return -1;
    }
}
