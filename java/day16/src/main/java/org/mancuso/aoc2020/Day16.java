package org.mancuso.aoc2020;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day16 extends AbstractDay {


    @Override
    public String name() {
        return "Day16";
    }

    public Input parseInput(List<String> lines) {
        Input result = new Input();
        result.rules = new HashMap<>();
        result.myTicket = new ArrayList<>();
        result.nearbyTickets = new ArrayList<>();
        int section = 0;
        for (String line : lines) {
            if (line.isBlank() || line.isEmpty()) continue;
            if (line.equals("your ticket:")) {
                section = 1;
            } else if (line.equals("nearby tickets:")) {
                section = 2;
            } else {
                switch (section) {
                    case 0:
                        String[] parts = line.split(":");
                        result.rules.put(parts[0], parseRanges(parts[1]));
                        break;
                    case 1:
                        result.myTicket = parseNumbers(line);
                        break;
                    case 2:
                        result.nearbyTickets.add(parseNumbers(line));
                        break;
                    default:
                        break;
                }
            }
        }
        return result;
    }

    @Override
    public String part1() {
        String result = null;
        try {
            Path p = Paths.get("day16/input");
            List<String> lines = Files.readAllLines(p);
            Input input = parseInput(lines);
            List<Integer> invalids = listInvalidNumbers(input);
            result = invalids.stream().reduce(Integer::sum).get().toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public long runPart2(Input input) {
        input.nearbyTickets = getValidTickets(input);
        Map<String, Integer> map = mapFields(input);
        if (verbose) {
            String[] fieldNames = new String[map.size()];
            for (String key : map.keySet()) {
                int idx = map.get(key);
                fieldNames[idx] = key;
            }
            for (int i = 0; i < fieldNames.length; i++) {
                println(String.format("%d:\t%s", i, fieldNames[i]));
            }
        }
        List<String> departureKeys = map.keySet().stream().filter(x -> x.startsWith("departure")).collect(Collectors.toList());
        long multResult = 1;
        for (String key : departureKeys) {
            int idx = map.get(key);
            Integer value = input.myTicket.get(idx);
            multResult = multResult * value;
        }
        return multResult;
    }

    @Override
    public String part2() {
        String result = null;
        try {
            Path p = Paths.get("day16/input");
            List<String> lines = Files.readAllLines(p);
            Input input = parseInput(lines);
            result = Long.toString(runPart2(input));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public List<Integer> parseNumbers(String s) {
        return Arrays.stream(s.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }

    public List<Range> parseRanges(String s) {
        List<Range> ranges = new ArrayList<>();
        String[] parts = s.split(" or ");
        for (String part : parts) {
            Range range = new Range(part);
            ranges.add(range);
        }
        return ranges;
    }

    public List<List<Integer>> getValidTickets(Input input) {
        List<Range> ranges = Range.consolidate(input.rules.values().stream().flatMap(List::stream).collect(Collectors.toList()));
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> ticket : input.nearbyTickets) {
            if (ticket.stream().allMatch(x -> Range.isIn(ranges, x))) {
                result.add(ticket);
            }
        }
        return result;
    }

    public List<Integer> listInvalidNumbers(Input input) {
        List<Range> ranges = Range.consolidate(input.rules.values().stream().flatMap(List::stream).collect(Collectors.toList()));
        List<Integer> result = new ArrayList<>();
        for (List<Integer> ticket : input.nearbyTickets) {
            result.addAll(ticket.stream().filter(x -> !Range.isIn(ranges, x)).collect(Collectors.toList()));
        }
        return result;
    }

    List<Integer> getField(List<List<Integer>> tickets, int fieldNumber) {
        List<Integer> results = tickets.stream().map(x -> x.get(fieldNumber)).collect(Collectors.toList());
        return results;
    }

    public Map<String, List<Integer>> getPotentialFieldMatches(Input input) {
        Map<String, List<Integer>> result = new HashMap<>();
        println("Operating with " + input.rules.keySet().size() + " rules");
        for (String field : input.rules.keySet()) {
            List<Range> fieldRules = input.rules.get(field);
            println("Finding potential matches for " + field);
            for (int fieldIdx = 0; fieldIdx < input.rules.size(); fieldIdx++) {
                List<Integer> fieldValues = getField(input.nearbyTickets, fieldIdx);
                if (fieldValues.stream().allMatch(x -> Range.isIn(fieldRules, x))) {
                    println("\tFound potential match: field " + fieldIdx);
                    if (result.containsKey(field)) {
                        result.get(field).add(fieldIdx);
                    } else {
                        List<Integer> indices = new ArrayList<>();
                        indices.add(fieldIdx);
                        result.put(field, indices);
                    }
                }
            }
        }
        return result;
    }

    public Map<String, Integer> mapFields(Input input) {
        Map<String, Integer> result = new HashMap<>();
        List<Integer> unDecidedFields = new ArrayList<>();
        for (int i = 0; i < input.rules.size(); i++) {
            unDecidedFields.add(i);
        }
        for (String field : input.rules.keySet()) {
            List<Range> fieldRules = input.rules.get(field);
            int foundIdx = -1;
            for (Integer fieldIdx : unDecidedFields) {
                List<Integer> fieldValues = getField(input.nearbyTickets, fieldIdx);
                if (fieldValues.stream().allMatch(x -> Range.isIn(fieldRules, x))) {
                    result.put(field, fieldIdx);
                    foundIdx = fieldIdx;
                    break;
                }
            }
            unDecidedFields.remove(Integer.valueOf(foundIdx));
        }
        return result;
    }
}
