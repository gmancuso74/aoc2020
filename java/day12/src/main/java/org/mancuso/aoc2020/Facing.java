package org.mancuso.aoc2020;

import java.util.Arrays;
import java.util.Optional;

public enum Facing {
    North(0),East(90), South(180), West(270);

    int degrees;

    private Facing(int value) {
        this.degrees=value;
    }

    public static Optional<Facing> from(Integer degrees) {
        return Arrays.stream(Facing.values()).filter(x->x.degrees==degrees).findFirst();
    }
}
