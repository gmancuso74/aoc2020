/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.mancuso.aoc2020.app;

import org.mancuso.aoc2020.AbstractDay;
import org.mancuso.aoc2020.Day1;
import org.mancuso.aoc2020.Day2;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("Main!");
        List<AbstractDay> days = new ArrayList<>();
        days.add(new Day1());
        days.add(new Day2());

        for (AbstractDay day : days) {
            try {
                day.run();
            } catch(Throwable ex) {
                System.out.println("Exception on day " + day.name()+": " +  ex.getMessage());
            }
        }
        for (AbstractDay day : days) {
            System.out.println(day.report());
        }
    }
}