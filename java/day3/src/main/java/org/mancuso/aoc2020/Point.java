package org.mancuso.aoc2020;

public class Point {
    public int x;
    public int y;

    public void apply(Slope m) {
        x += m.x;
        y += m.y;
    }

    public void transform(int mapWidth) {
        if (x >= mapWidth) {
            x = x % mapWidth;
        }
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    public String toString() {
        return "[" + x + "," + y + "]";
    }
}
