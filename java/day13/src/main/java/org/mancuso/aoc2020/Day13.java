package org.mancuso.aoc2020;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day13 extends AbstractDay {
    long T0;
    List<Integer> busses;
    List<BusRec> busRecs = new ArrayList<BusRec>();
    long departureTime;
    Integer departingBus;
    long answerPart1;

    public Integer departingBus(long Tn, List<Integer> busses) {
        for (Integer bus : busses) {
            if (Tn % bus == 0) return bus;
        }
        return null;
    }

    public void Initialize(List<String> lines) {
        T0 = Integer.parseInt(lines.get(0));
        String[] busDesc = lines.get(1).split(",");
        busses = Arrays.stream(busDesc).filter(x -> !x.equals("x")).map(Integer::parseInt).collect(Collectors.toList());
        for (int i = 0; i < busDesc.length; i++) {
            if (busDesc[i].equals("x")) continue;
            busRecs.add(new BusRec(i, Integer.parseInt(busDesc[i])));
        }
    }

    public boolean validate(long Tn, List<BusRec> busRecs) {
        for (BusRec bus : busRecs) {
            if ((Tn + bus.offset) % bus.busId != 0) return false;
        }
        return true;
    }

    public void doPart1() {
        for (long Tn = T0; Tn < Long.MAX_VALUE; Tn++) {
            departingBus = departingBus(Tn, busses);
            if (departingBus != null) {
                departureTime = Tn;
                break;
            }
        }
        answerPart1 = departingBus * (departureTime - T0);
    }

    public void doPart2() {
        for (long Tn = 0; Tn < Long.MAX_VALUE; Tn++) {
            if (validate(Tn, busRecs)) {
                departureTime = Tn;
                break;
            }
            if (verbose && (Tn % 100000 == 0)) System.out.println("Checkpoint: " + Tn);
        }
    }

    @Override
    public String name() {
        return "Day13";
    }

    @Override
    public String part1() {
        Path p = Paths.get("day13/input");
        String result = null;
        try {
            List<String> lines = Files.readAllLines(p);
            Initialize(lines);
            doPart1();
            result = Long.toString(answerPart1);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return result;
    }

    @Override
    public String part2() {
        Path p = Paths.get("day13/input");
        String result = null;
        try {
            List<String> lines = Files.readAllLines(p);
            Initialize(lines);
            verbose=true;
            doPart2();
            result = Long.toString(departureTime);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return result;
    }
}
