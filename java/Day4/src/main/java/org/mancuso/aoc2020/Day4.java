package org.mancuso.aoc2020;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 extends AbstractDay {
    public static Map<Field, List<Predicate<String>>> rules = new HashMap<>();
    public boolean verbose = false;
    public static boolean oneOf(String value, String... values) {
        String regex = String.join("|", values);
        return value.matches(regex);
    }

    public static boolean between(String value, int l, int r) {
        try {
            int val = Integer.parseInt(value);
            return (val >= l && val <= r);
        } catch (Exception ex) {
            return false;
        }
    }

    static {
        for (Field field : Field.values()) {
            ArrayList<Predicate<String>> rules;
            switch (field) {
                case byr:
                    rules = new ArrayList<>();
                    rules.add(x -> between(x, 1920, 2002));
                    Day4.rules.put(field, rules);
                    break;
                case ecl:
                    rules = new ArrayList<>();
                    rules.add(x -> oneOf(x, "amb", "blu", "brn", "gry", "grn", "hzl", "oth"));
                    Day4.rules.put(field, rules);
                    break;
                case eyr:
                    rules = new ArrayList<>();
                    rules.add(x -> between(x, 2020, 2030));
                    Day4.rules.put(field, rules);
                    break;
                case hcl:
                    rules = new ArrayList<>();
                    rules.add(x -> x.matches("#[0-9a-f]{6}"));
                    Day4.rules.put(field, rules);
                    break;
                case hgt:
                    rules = new ArrayList<>();
                    String regex = "(?<val>[0-9]+)(?<unit>in|cm)";
                    Pattern p = Pattern.compile(regex);
                    rules.add(x -> {
                        Matcher m = p.matcher(x);
                        if (m.matches()) {
                            String val = m.group("val");
                            String unit = m.group("unit");
                            if ("cm".equals(unit)) {
                                return between(val, 150, 193);
                            } else if ("in".equals(unit)) {
                                return between(val, 59, 76);
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    });
                    Day4.rules.put(field, rules);
                    break;
                case iyr:
                    rules = new ArrayList<>();
                    rules.add(x -> between(x, 2010, 2020));
                    Day4.rules.put(field, rules);
                    break;
                case pid:
                    rules = new ArrayList<>();
                    rules.add(x-> x.matches("\\d{9}"));
                    Day4.rules.put(field, rules);
                    break;
                default:
                    rules = new ArrayList<>();
                    Day4.rules.put(field, rules);
                    break;
            }
        }
    }

    public int process(List<String> lines, boolean validate) {
        List<Map<Field, String>> passports = new ArrayList<>();
        Map<Field, String> passport = new HashMap<>();
        for (String line : lines) {
            try {
                if (line == null || line.trim().length() == 0) {
                    passports.add(passport);
                    passport = new HashMap<>();
                    continue;
                }
                String[] tokens = line.split("\\s");
                for (String token : tokens) {
                    String[] pair = token.split(":");
                    String keyCode = pair[0];
                    String value = pair[1];
                    Field key = Field.byCode(keyCode);
                    passport.put(key, value);
                }
            } catch (Exception ex) {
                System.out.println("Error [" + ex.getMessage() + "] processing input: " + line);
            }
        }
        passports.add(passport);
        int valid = 0;
        for (Map<Field, String> pp : passports) {
            if (validate) {
                boolean passedAllRules = true;
                if (!pp.keySet().containsAll(Field.getRequired())) {
                    passedAllRules = false;
                }
                for (Field key : pp.keySet()) {
                    for (Predicate<String> predicate : rules.get(key)) {
                        String value=pp.get(key);
                        boolean pass = predicate.test(value);
                        if (!pass) {
                            passedAllRules = false;
                            if(verbose) {System.out.printf("[%s] failed rule for [%s]%n",value,key.name());}
                        }
                    }
                }
                if (passedAllRules) {
                    valid++;
                }
            } else {
                if (pp.keySet().containsAll(Field.getRequired())) {
                    valid++;
                }
            }
        }
        return valid;
    }

    @Override
    public String name() {
        return "Day4";
    }

    @Override
    public String part1() {
        String result = null;
        try {
            Path p = Paths.get("day4/input");
            List<String> lines = Files.readAllLines(p);
            Day4 d4 = new Day4();
            int valid = d4.process(lines, false);
            result = String.valueOf(valid);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public String part2() {
        String result = null;
        try {
            Path p = Paths.get("day4/input");
            List<String> lines = Files.readAllLines(p);
            Day4 d4 = new Day4();
            int valid = d4.process(lines, true);
            result = String.valueOf(valid);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
