package org.mancuso.aoc2020;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 extends AbstractDay {
    class Content {
        String color;
        int count;
    }

    class Color {
        String color;
        final Set<String> parents;

        public Color(String color) {
            this.color = color;
            this.parents = new HashSet<>();
        }
    }

    Map<String, Color> parseRulesForParents(List<String> lines) {
        Map<String, Color> parents = new HashMap<>();//map for ease of searching
        for (String line : lines) {
            Matcher m = rulePattern.matcher(line);
            if (m.matches()) {
                String parentColor = m.group(1);
                List<String> possibleChildren = parseContentsForParents(m.group(2));
                if (possibleChildren == null) {
                    continue;
                }
                for (String child : possibleChildren) {
                    if (parents.containsKey(child)) {
                        parents.get(child).parents.add(parentColor);
                    } else {
                        Color c = new Color(child);
                        c.parents.add(parentColor);
                        parents.put(child, c);
                    }
                }
            }
        }
        return parents;
    }

    Map<String, List<Content>> parseRulesForContainment(List<String> lines) {
        Map<String, List<Day7.Content>> rules = new HashMap<>();
        for (String line : lines) {
            Matcher m = rulePattern.matcher(line);
            if (m.matches()) {
                String container = m.group(1);
                printf("%s, containing: [%s]\n", container, m.group(2));
                List<Day7.Content> contents = parseContentsForContainment(m.group(2));
                rules.put(container, contents);
            } else {
                System.out.printf("Line [%s] doesn't match the rule pattern.\n", line);
            }
        }
        return rules;
    }

    public List<String> parseContentsForParents(String group) {
        String[] bags = group.split(",");
        List<String> result = new ArrayList<>();
        for (String bag : bags) {
            bag = bag.trim();
            Matcher m = contentPattern.matcher(bag);
            if (m.matches()) {
                if (bag.equals("no other bags.")) {
                    return null;
                } else {
                    result.add(m.group("color"));
                }
            } else {
                System.out.println("No Match for " + bag);
            }
        }
        return result;
    }

    public Set<String> ancestorsOf(String color, Map<String, Color> rules) {
        Set<String> ancestors = new HashSet<>();
        Color scion = rules.get(color);
        if (scion != null) {
            Set<String> parents = scion.parents;
            for (String parent : parents) {
                if (ancestors.add(parent)) {
                    ancestors.addAll(ancestorsOf(parent, rules));
                }
            }
        }
        return ancestors;
    }

    public List<Content> parseContentsForContainment(String group) {
        String[] bags = group.split(",");
        List<Content> result = new ArrayList<>();
        for (String bag : bags) {
            bag = bag.trim();
            Matcher m = contentPattern.matcher(bag);
            if (m.matches()) {
                if (bag.equals("no other bags.")) {
                    return null;
                } else {
                    Content c = new Content();

                    c.count = Integer.parseInt(m.group("count"));
                    c.color = m.group("color");
                    result.add(c);
                    printf("\t[%d] [%s]\n", c.count, c.color);
                }
            } else {
                System.out.println("No Match for " + bag);
            }
        }
        return result;
    }


    Map<String, Integer> countMemos = new HashMap<>();

    public int countBags(String color, Map<String, List<Day7.Content>> rules) {
        int bagCount = 0;
        if (countMemos.containsKey(color)) {
            printf("\tUsing pre-calculated bag count [%d] for [%s]\n", countMemos.get(color), color);
            return countMemos.get(color);
        }
        if (rules.containsKey(color)) {
            if (rules.get(color) == null) {
                printf("\tReturning 0; no other bags for color [%s]\n", color);
                countMemos.put(color,0);
                return 0;
            } else {
                for (Day7.Content rule : rules.get(color)) {
                    int singleBagCount=countBags(rule.color, rules);
                    int bags = rule.count + rule.count * singleBagCount;
                    printf("\t[%d] bags in [%s]\n", bags, rule.color);
                    countMemos.put(rule.color, singleBagCount);
                    bagCount += (bags);
                }
            }
        }
        printf("[%s] contains [%d]\n", color, bagCount);
        return bagCount;
    }


    @Override
    public String name() {
        return "Day7";
    }

    public static Pattern rulePattern = Pattern.compile("(.*) bags contain ((\\d+ .* bag\\S*)|(no other bags.))");
    public static Pattern contentPattern = Pattern.compile("((?<count>\\d)+ (?<color>.*) bag\\S*)|(no other bags.)");

    @Override
    public String part1() {
        String result = null;
        try {
            Path p = Paths.get("day7/input");
            List<String> lines = Files.readAllLines(p);
            var rules = parseRulesForParents(lines);
            Set<String> ancestors = ancestorsOf("shiny gold", rules);
            result = String.valueOf(ancestors.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public String part2() {
        String result = null;
        try {
            Path p = Paths.get("day7/input");
            List<String> lines = Files.readAllLines(p);
            Map<String, List<Day7.Content>> rules = parseRulesForContainment(lines);
            int count = countBags("shiny gold", rules);
            result = String.valueOf(count);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
