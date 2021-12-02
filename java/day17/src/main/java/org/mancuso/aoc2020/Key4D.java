package org.mancuso.aoc2020;

import java.util.HashSet;
import java.util.Set;

public class Key4D {
    final int x;
    final int y;
    final int z;
    final int w;

    public Key4D(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Key4D(Key key3d) {
        this(key3d.x, key3d.y, key3d.z, 0);
    }

    public boolean isNeighbor(Key4D input) {
        int dX = (input.x - this.x);
        int dY = (input.y - this.y);
        int dZ = (input.z - this.z);
        int dW = (input.w - this.w);
        return (dX < 2 && dX > -2) && (dY < 2 && dY > -2) && (dZ < 2 && dZ > -2) && (dW < 2 && dW > -2);
    }

    public Set<Key4D> neighbors() {
        Set<Key4D> result = new HashSet<>();
        for (int m = w - 1; m < w + 2; m++)
            for (int k = z - 1; k < z + 2; k++)
                for (int j = y - 1; j < y + 2; j++)
                    for (int i = x - 1; i < x + 2; i++)
                        result.add(new Key4D(i, j, k, m));
        result.remove(this);
        return result;
    }

    //we override equals & hashCode so we can use this as a key without jumping through comparator hoops
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Key4D other = (Key4D) obj;
        if (this.x == other.x && this.y == other.y && this.z == other.z && this.w == other.w) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 5 * x + 7 * y + 11 * z + 13 * w;
    }

    @Override
    public String toString() {
        return String.format("[%d,%d,%d,%d]", x, y, z, w);
    }

}