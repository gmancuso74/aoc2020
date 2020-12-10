package org.mancuso.aoc2020;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day6 extends AbstractDay {

    @Override
    public String name() {
        return "Day6";
    }


    public int sum(List<Set<Character>> groups){
        int sum = 0;
        for(Set<Character> group : groups) {
            sum += group.size();
        }
        return sum;
    }

    @Override
    public String part1() {
        String result = null;
        try {
            Path p = Paths.get("day6/input");
            List<String> lines = Files.readAllLines(p);
            List<Set<Character>> groups = load(lines);
            result=String.valueOf(sum(groups));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public String part2() {
        String result = null;
        try {
            Path p = Paths.get("day6/input");
            List<String> lines = Files.readAllLines(p);
            List<Set<Character>> groups = loadPart2(lines);
            result=String.valueOf(sum(groups));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public List<Set<Character>> load(List<String> lines) {
        Set<Character> group = new HashSet<>();
        List<Set<Character>> groups = new ArrayList<>();
        for(String line : lines) {
            if(line.isBlank()) {
                groups.add(group);
                group=new HashSet<>();
            }
            for(Character c : line.toCharArray()) {
               group.add(c);
            }
        }
        groups.add(group);
        return groups;
    }
    public Set<Character> loadPerson(String line) {
        if(line.isBlank()) return null;
        Set<Character> person = new HashSet<>();
        for(Character c : line.toCharArray()) {
            person.add(c);
        }
        return person;
    }

    public List<Set<Character>> loadPart2(List<String> lines) {
        List<Set<Character>> groups = new ArrayList<>();
        Set<Character> group = new HashSet<>();
        boolean newgroup=true;
        for(String line : lines) {
            Set<Character> person=loadPerson(line);
            if(person==null) {
                //new group
                groups.add(group);
                group=new HashSet<>();
                newgroup=true;
                continue;
            } else {
                if(newgroup){
                    newgroup=false;
                    group.addAll(person);
                } else {
                    group.retainAll(person);
                }
            }
        }
        groups.add(group);
        return groups;
    }
}
