package org.mancuso.aoc2020;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Range implements Comparable<Range> {
    public int start;
    public int end;

    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public Range(String rangeDesc) {
        String[] tokens = rangeDesc.split("-");
        if (tokens.length != 2) {
            throw new RuntimeException("Unable to parse range for " + rangeDesc);
        }
        this.start = Integer.parseInt(tokens[0].trim());
        this.end = Integer.parseInt(tokens[1].trim());
    }

    public static List<Range> consolidate(List<Range> ranges) {
        ranges.sort(null);
        Stack<Range> stack = new Stack<>();
        for (Range range : ranges) {
            if (stack.isEmpty()) {
                stack.push(range);
                continue;
            }
            Range peek = stack.peek();
            if (range.overlaps(peek)) {
                peek.start = Math.min(peek.start, range.start);
                peek.end = Math.max(peek.end, range.end);
            } else {
                stack.push(range);
            }
        }
        return stack.stream().collect(Collectors.toList());
    }

    public boolean contains(int val) {
        return val >= start && val <= end;
    }

    public boolean overlaps(Range r) {
        int maxStart = Math.max(this.start, r.start);
        int minEnd = Math.min(this.end, r.end);
        return maxStart < minEnd;
    }

    @Override
    public int compareTo(Range o) {
        if (o.start != start) {
            return Integer.compare(start, o.start);
        }
        return Integer.compare(end, o.end);
    }

    public static boolean isIn(List<Range> iter, Range range) {
        return iter.stream().anyMatch(x -> x.compareTo(range) == 0);
    }

    public static boolean isIn(List<Range> iter, int value) {
        return iter.stream().anyMatch(x -> x.contains(value));
    }
}
