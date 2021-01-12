package org.mancuso.aoc2020;

import static org.mancuso.aoc2020.Facing.*;

public class Day2Ferry {
    public Facing direction = East;
    public Point position = new Point(0, 0);
    public Point waypoint = new Point(10, 1);

    public void MoveWaypoint(Facing direction, Integer value) {
        switch (direction) {
            case North:
                waypoint.y += value;
                break;
            case East:
                waypoint.x += value;
                break;
            case South:
                waypoint.y -= value;
                break;
            case West:
                waypoint.x -= value;
                break;
            default:
                break;
        }
    }

    public void Move(Integer value) {
        for (int i = 0; i < value; i++) {
            position.x += waypoint.x;
            position.y += waypoint.y;
        }
    }

    public void Rotate(Integer degrees) {
        if (degrees % 90 != 0) throw new RuntimeException("Must be even multiple of 90 degrees");
        if(degrees<0) {
            degrees=360+degrees;
        }
        int times = degrees / 90;
        for (int i = 0; i < times; i++) {
            int newx = waypoint.y;
            int newy = waypoint.x * -1;
            waypoint.x=newx;
            waypoint.y=newy;
        }
    }

    public void Apply(Command cmd) {
        switch (cmd.command) {
            case N:
                MoveWaypoint(North, cmd.value);
                break;
            case S:
                MoveWaypoint(South, cmd.value);
                break;
            case E:
                MoveWaypoint(East, cmd.value);
                break;
            case W:
                MoveWaypoint(West, cmd.value);
                break;
            case L:
                Rotate(-1 * cmd.value);
                break;
            case R:
                Rotate(cmd.value);
                break;
            case F:
                Move(cmd.value);
        }
    }
}
