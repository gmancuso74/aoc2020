package org.mancuso.aoc2020;

import static org.mancuso.aoc2020.Facing.*;

public class Ferry {
    public Facing direction = East;
    public Point position = new Point(0, 0);

    public Facing Turn(Integer degrees) {
        if (degrees == null || (degrees % 90 != 0)) {
            throw new RuntimeException("Only turns of 90 degree multiples supported");
        }
        int curFacing = direction.degrees;
        int nextFacing = curFacing + degrees;
        if (nextFacing < 0) {
            nextFacing = nextFacing + 360;
        }
        if (nextFacing >= 360) {
            nextFacing = nextFacing - 360;
        }
        var result = direction.from(nextFacing);
        if (result.isPresent()) {
            this.direction=result.get();
            return this.direction;
        } else {
            throw new RuntimeException("Turn results in unknown Facing " + nextFacing);
        }
    }

    public Point Move(Integer distance) {
        return Move(this.direction, distance);
    }

    public Point Move(Facing Direction, Integer distance) {
        Point newPoint = new Point(position.x, position.y);
        switch (Direction) {
            case North:
                newPoint.y = position.y + distance;
                break;
            case East:
                newPoint.x = position.x + distance;
                break;
            case South:
                newPoint.y = position.y - distance;
                break;
            case West:
                newPoint.x = position.x - distance;
                break;
            default:
                break;
        }
        position = newPoint;
        return newPoint;
    }

    public String status() {
        return String.format("Position(%d,%d) | Facing %s", position.x, position.y, this.direction.name());
    }

    public void Apply(Command cmd) {
        switch (cmd.command) {
            case N:
                Move(North, cmd.value);
                break;
            case S:
                Move(South, cmd.value);
                break;
            case E:
                Move(East, cmd.value);
                break;
            case W:
                Move(West, cmd.value);
                break;
            case L:
                Turn(-1 * cmd.value);
                break;
            case R:
                Turn(cmd.value);
                break;
            case F:
                Move(cmd.value);
        }
    }
}
