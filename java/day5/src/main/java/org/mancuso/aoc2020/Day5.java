package org.mancuso.aoc2020;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day5 extends AbstractDay {

    @Override
    public String name() {
        return "Day5";
    }

    public int getRow(String input) {
        input = input.substring(0, 7);
        input = input.replace("F", "0");
        input = input.replace("B", "1");
        return Byte.toUnsignedInt(Byte.parseByte(input, 2));
    }

    public int getSeat(String input) {
        input = input.substring(7, 10);
        input = input.replace("L", "0");
        input = input.replace("R", "1");
        input = String.format("%8s", input);
        input = input.replace(" ", "0");
        return Byte.toUnsignedInt(Byte.parseByte(input, 2));
    }

    public int getSeatID(String input){
        int row=getRow(input);
        int seat=getSeat(input);
        return 8*row+seat;
    }

    public int findHighest(List<String> lines) {
        int highest=0;
        for(String line: lines){
           int seatID=getSeatID(line);
           if(seatID>highest){
               highest=seatID;
           }
        }
        return highest;
    }

    public int findMySeat(List<String> lines) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        Set<Integer> seats=new HashSet<>();
        for(String line: lines){
            int seatID=getSeatID(line);
            if(seatID<min){
                min=seatID;
            }
            if(seatID>max){
                max=seatID;
            }
            seats.add(seatID);
        }
        Set<Integer> allSeats= IntStream.rangeClosed(min, max).boxed().collect(Collectors.toSet());
        allSeats.removeAll(seats);
        int aSeat = 0;
        for(Integer empty: allSeats){
            aSeat=empty;
        }
        return aSeat;
    }

    @Override
    public String part1() {
        String result = null;
        try {
            Path p = Paths.get("day5/input");
            List<String> lines = Files.readAllLines(p);
            int highest = new Day5().findHighest(lines);
            result=String.valueOf(highest);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public String part2() {
        String result = null;
        try {
            Path p = Paths.get("day5/input");
            List<String> lines = Files.readAllLines(p);
            int mySeat = new Day5().findMySeat(lines);
            result=String.valueOf(mySeat);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
