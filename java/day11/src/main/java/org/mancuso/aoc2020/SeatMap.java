package org.mancuso.aoc2020;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.mancuso.aoc2020.Square.Filled;
import static org.mancuso.aoc2020.Square.Floor;

public class SeatMap {
    public static NTuple<Integer> nw = buildRay(-1, -1);
    public static NTuple<Integer> n = buildRay(-1, 0);
    public static NTuple<Integer> ne = buildRay(-1, 1);
    public static NTuple<Integer> w = buildRay(0, -1);
    public static NTuple<Integer> e = buildRay(0, 1);
    public static NTuple<Integer> sw = buildRay(1, -1);
    public static NTuple<Integer> s = buildRay(1, 0);
    public static NTuple<Integer> se = buildRay(1, 1);
    public static List<NTuple<Integer>> directions;
    static {
        directions= new ArrayList<NTuple<Integer>>();
        directions.add(nw);
        directions.add(n);
        directions.add(ne);
        directions.add(w);
        directions.add(e);
        directions.add(sw);
        directions.add(s);
        directions.add(se);
    }

    public static NTuple<Integer> buildRay(int rVec, int cVec) {
        NTuple<Integer> ray = new NTuple<>(2);
        ray.set(0, rVec);
        ray.set(1, cVec);
        return ray;
    }

    public interface Walker {
        Square apply(int row, int col);
    }

    final public Square[][] squares;
    final int rowCount;
    final int colCount;

    public boolean applyChanged = false;

    public SeatMap(Square[][] in) {
        this.rowCount = in.length;
        this.colCount = in[0].length;
        this.squares = new Square[rowCount][colCount];
        for (int row = 0; row < rowCount; row++) {
            squares[row] = in[row].clone();
        }
    }

    public Square squareAt(int row, int col) {
        if (row < 0 || row >= rowCount) return null;
        if (col < 0 || col >= colCount) return null;
        return squares[row][col];
    }

    public SeatMap apply(Walker walker) {
        SeatMap result = new SeatMap(squares);
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                result.squares[row][col] = walker.apply(row, col);
                if (result.squareAt(row, col) != squares[row][col]) {
                    result.applyChanged = true;
                }
            }
        }
        return result;
    }

    void addIfNotNull(List<Square> squares, Square square) {
        if (square != null) {
            squares.add(square);
        }
    }

    List<Square> adjacentSquares(int row, int col) {
        List<Square> result = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            int thisRow = row + i;
            if (thisRow < 0) continue; //skip if row index is negative
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) continue;//skip the center square
                int thisCol = col + j;
                if (thisCol < 0) continue; //skip if column index is negative
                addIfNotNull(result, squareAt(thisRow, thisCol));
            }
        }
        return result;
    }

    List<Square> raySquares(int row, int col) {
        List<Square> result = new ArrayList<>();
        for(NTuple<Integer> ray : directions) {
            result.add(raySeat(ray,row,col));
        }
        return result;
    }

    Square raySeat(NTuple<Integer> ray, int row, int col) {
        if (ray.getSize() != 2) throw new RuntimeException("Need ray to be NTuple<Integer> of size 2");
        int thisRow = row;
        int thisCol = col;
        int rRay = ray.get(0);
        int cRay = ray.get(1);
        Square thisSquare = Floor;//if the "next" square is off the grid, consider it floor
        do {
            thisRow = thisRow + rRay;
            thisCol = thisCol + cRay;
            var nextSquare = squareAt(thisRow, thisCol);
            if (nextSquare == null) return thisSquare;
            thisSquare = nextSquare;
        } while (thisSquare == Floor);
        return thisSquare;
    }

    int filledSeats() {
        int filledSeats = 0;
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                if (squareAt(row, col) == Filled) {
                    filledSeats++;
                }
            }
        }
        return filledSeats;
    }

    Square printer(int x, int y) {
        if (x == 0 && y == 0) {
            for (int i = 0; i < colCount; i++) {
                System.out.print("=");
            }
            System.out.println();
        }
        Square square = squareAt(x, y);
        switch (square) {
            case Empty:
                System.out.print("L");
                break;
            case Filled:
                System.out.print("#");
                break;
            case Floor:
                System.out.print(".");
                break;
            default:
                throw new RuntimeException("Unknown square: " + square.name());
        }
        if (y == colCount - 1) {
            System.out.println();
        }
        return squareAt(x, y);
    }
}
