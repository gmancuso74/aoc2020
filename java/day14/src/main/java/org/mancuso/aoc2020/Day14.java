package org.mancuso.aoc2020;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14 extends AbstractDay {
    public int size = 36;
    public List<Pair<Integer, Boolean>> mask = new ArrayList<>();
    public Map<Long,Long> memory = new HashMap<>();
    public static String memCmdPatternStr = "mem\\[(.*)\\] = (.*)";
    public static Pattern memCmdPattern = Pattern.compile(memCmdPatternStr);
    public static String maskCmdPatternStr = "mask = (.*)";
    public static Pattern maskCmdPattern = Pattern.compile(maskCmdPatternStr);

    @Override
    public String name() {
        return "Day14";
    }

    @Override
    public String part1() {
        Path p = Paths.get("day14/input");
        String result=null;
        try {
            List<String> lines = Files.readAllLines(p);
            run(lines);
            result=Long.toString(getSum());
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public String part2() {
        Path p = Paths.get("day14/input");
        String result=null;
        memory=new HashMap<>();
        try {
            List<String> lines = Files.readAllLines(p);
            runPart2(lines);
            result=Long.toString(getSum());
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public List<Pair<Integer, Boolean>> parseMask(char[] maskStr) {
        List<Pair<Integer, Boolean>> result = new ArrayList<>();
        for (int i = maskStr.length - 1, idx = 0; i >= 0; i--, idx++) {
            char c = maskStr[i];
            switch (c) {
                case '0':
                case '1':
                    result.add(new Pair<>(idx, c == '1'));
                    break;
                default:
                    break;
            }
        }
        return result;
    }

    public String toBitString(BitSet input) {
        long[] vals = input.toLongArray();
        String valStr = Long.toBinaryString(vals[0]);
        String formatStr = "%1$" + size + "s";
        return String.format(formatStr, valStr).replace(' ', '0');
    }

    public BitSet fromString(String input) {
        return BitSet.valueOf(new long[]{Long.parseLong(input, 2)});
    }

    public void setMask(String maskStr) {
        this.mask = parseMask(maskStr.toCharArray());
    }

    public Long apply(List<Pair<Integer, Boolean>> mask, Long value) {
        var bs = BitSet.valueOf(new long[]{value});
        var resultBS = apply(mask, bs);
        return resultBS.toLongArray()[0];
    }

    public BitSet apply(List<Pair<Integer, Boolean>> mask, BitSet value) {
        BitSet result = BitSet.valueOf(value.toByteArray());
        for (var pair : mask) {
            result.set(pair.a, pair.b);
        }
        return result;
    }

    public BitSet apply(char[] maskStr, BitSet value) {
        List<Pair<Integer, Boolean>> mask = parseMask(maskStr);
        return apply(mask, value);
    }


    public void run(List<String> program) {
        for (String line : program) {
            Matcher maskMatcher = maskCmdPattern.matcher(line);
            Matcher memMatcher = memCmdPattern.matcher(line);
            if (maskMatcher.matches()) {
                setMask(maskMatcher.group(1));
            } else if (memMatcher.matches()) {
                setMemory(memMatcher.group(1), memMatcher.group(2), this.mask);
            } else {
                throw new RuntimeException("Invalid command: " + line);
            }
        }
    }
    public void runPart2(List<String> program) {
        String maskStr= "000000000000000000000000000000000000";
        for (String line : program) {
            Matcher maskMatcher = maskCmdPattern.matcher(line);
            Matcher memMatcher = memCmdPattern.matcher(line);
            if (maskMatcher.matches()) {
                maskStr=maskMatcher.group(1);
            } else if (memMatcher.matches()) {
                setMemoryPart2(applyMask(maskStr,memMatcher.group(1)),memMatcher.group(2));
            } else {
                throw new RuntimeException("Invalid command: " + line);
            }
        }
    }



    public long getSum() {
       return memory.values().stream().mapToLong(Long::longValue).sum();
    }

    private void setMemory(String addressStr, String valueStr, List<Pair<Integer, Boolean>> mask) {
        Long address = Long.parseLong(addressStr);
        Long value = Long.parseLong(valueStr);
        value = apply(mask, value);
        memory.put(address,value);
    }

    private void setMemoryPart2(List<Long> addresses, String value) {
        Long val = Long.parseLong(value);
        for(Long addr : addresses) {
            memory.put(addr,val);
        }
    }

    public List<Long> decodeMask(String input) {
        List<Long> results = new ArrayList<>();
        if(!input.contains("X")){
            results.add( Long.parseLong(input, 2));
        }else {
            String input1=input.replaceFirst("X","0");
            String input2=input.replaceFirst("X","1");
            results.addAll(decodeMask(input1));
            results.addAll(decodeMask(input2));
        }
        return results;
    }

    public List<Long> applyMask(String maskStr,String inputStr) {
        Long input =Long.parseLong(inputStr);
        return applyMask(maskStr,input);
    }

    public List<Long> applyMask(String maskStr,Long input) {
        String inputStr=toBitString(BitSet.valueOf(new long[]{input}));
        String maskedString = "";
        for(int i=0;i<this.size;i++) {
            char inChar = inputStr.charAt(i);
            char maskChar=maskStr.charAt(i);
            switch(maskChar) {
                case '0':
                    maskedString+=inChar;
                    break;
                case '1':
                    maskedString+='1';
                    break;
                case 'X':
                    maskedString+='X';
                    break;
                default:
                    break;
            }
        }
        List<Long> addrs = decodeMask(maskedString);
        return addrs;
    }

}
