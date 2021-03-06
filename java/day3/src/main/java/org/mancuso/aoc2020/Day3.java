/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.mancuso.aoc2020;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day3 extends AbstractDay {


    public String name() {
        return "Day3";
    }

    boolean[][] mapTemplate;
    int rows;
    int columns;
    boolean verbose = false;

    public void load(String filename) {
        Path path = Paths.get(filename);
        try {
            List<String> lines = Files.readAllLines(path);
            rows = lines.size();
            columns = lines.get(0).length();
            for (int y = 0; y < rows; y++) {
                if (y == 0) {
                    mapTemplate = new boolean[columns][rows];

                }
                for (int x = 0; x < columns; x++) {
                    char value = lines.get(y).charAt(x);
                    switch (value) {
                        case '#':
                            mapTemplate[x][y] = true;
                            break;
                        case '.':
                            mapTemplate[x][y] = false;
                            break;
                        default:
                            System.out.println("Unexpected input at " + x + "\t" + y + ":  + value");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int ride(Point start, Slope s) {
        int treesHit = 0;
        Point loc = new Point(start);
        if(verbose) System.out.println("Starting at " + start + " : " + mapTemplate[loc.x][loc.y]);
        do {
            if (mapTemplate[loc.x][loc.y]) {
                treesHit++;
            }
            loc.apply(s);
            loc.transform(mapTemplate.length);
            if (verbose) {
                if (loc.y < rows) {
                    System.out.print("Riding through " + loc + " : ");
                    System.out.println(mapTemplate[loc.x][loc.y]);
                } else {
                    System.out.println("Done!");
                }
            }
        } while (loc.y < rows);
        return treesHit;
    }

    public String part1() {
        Point p = new Point(0, 0);
        load("day3/input");
        int treesHit = ride(p, new Slope(3, 1));
        return String.valueOf(treesHit);
    }

    public String part2() {
        Point p = new Point(0, 0);
        load("day3/input");
        Slope[] slopes = new Slope[]{
                new Slope(1,1),
                new Slope(3,1),
                new Slope(5,1),
                new Slope(7,1),
                new Slope(1,2)
        };
        BigInteger product=BigInteger.ONE;
        for (Slope s : slopes ) {
            BigInteger hits = BigInteger.valueOf(ride(p,s));
            product=product.multiply(hits);
        }
        return String.valueOf(product);
    }

}
