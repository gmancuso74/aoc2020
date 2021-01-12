package org.mancuso.aoc2020;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.mancuso.aoc2020.Square.*;

public class Day11 extends AbstractDay {

    public SeatMap buildMap(List<String> lines) {
        int rowCount = lines.size();
        int colCount = lines.get(0).length();
        Square[][] map = new Square[rowCount][colCount];
        for (int row = 0; row < rowCount; row++) {
            String line = lines.get(row);
            for (int col = 0; col < colCount; col++) {
                char thisChar = line.charAt(col);
                switch (thisChar) {
                    case '.':
                        map[row][col] = Floor;
                        break;
                    case 'L':
                        map[row][col] = Square.Empty;
                        break;
                    case '#':
                        map[row][col] = Filled;
                        break;
                    default:
                        throw new RuntimeException("Invalid square descriptor: " + thisChar);

                }
            }
        }
        return new SeatMap(map);
    }

    public SeatMap part1Walker(SeatMap map) {
        SeatMap.Walker p1w = (row, col) -> {
            List<Square> adjSeats = map.adjacentSquares(row, col);
            Square thisSquare = map.squareAt(row, col);
            if (thisSquare == Floor) {
                return Floor;
            } else {
                long adjFilledCount = adjSeats.stream().filter(s -> Filled == s).count();
                if (thisSquare == Empty && adjFilledCount == 0) {
                    return Filled;
                }
                if (thisSquare == Filled && adjFilledCount >= 4) {
                    return Empty;
                }
                return thisSquare;
            }
        };
        SeatMap result = map.apply(p1w);
        if (verbose) {
            result.apply(result::printer);
        }
        return result;
    }

    public SeatMap part2Walker(SeatMap map) {
        SeatMap.Walker p2w = (row, col) -> {
            Square thisSquare = map.squareAt(row, col);
            if (thisSquare == Floor) {
                return Floor;
            } else {
                List<Square> raySeats = map.raySquares(row, col);
                long adjFilledCount = raySeats.stream().filter(s -> Filled == s).count();
                if (thisSquare == Empty && adjFilledCount == 0) {
                    return Filled;
                }
                if (thisSquare == Filled && adjFilledCount >= 5) {
                    return Empty;
                }
                return thisSquare;
            }
        };
        SeatMap result = map.apply(p2w);
        if (verbose) {
            result.apply(result::printer);
        }
        return result;
    }

    @Override
    public String name() {
        return "Day11";
    }

    @Override
    public String part1() {
        String result = null;
        Path p = Paths.get("day11/input");
        try {
            List<String> lines = Files.readAllLines(p);
            SeatMap map = buildMap(lines);
            int applyCount = 0;
            do {
                map = part1Walker(map);
                applyCount++;
            } while (map.applyChanged);
            result = String.format("%d", map.filledSeats());
        } catch (Exception ex) {
            return null;
        }
        return result;
    }

    @Override
    public String part2() {
        String result = null;
        Path p = Paths.get("day11/input");
        try {
            List<String> lines = Files.readAllLines(p);
            SeatMap map = buildMap(lines);
            int applyCount = 0;
            do {
                map = part2Walker(map);
                applyCount++;
            } while (map.applyChanged);
            result = String.format("%d", map.filledSeats());
        } catch (Exception ex) {
            return null;
        }
        return result;
    }
}
