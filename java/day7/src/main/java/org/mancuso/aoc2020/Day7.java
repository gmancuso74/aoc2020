package org.mancuso.aoc2020;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day7 extends AbstractDay {

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

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return result;
        }


}
