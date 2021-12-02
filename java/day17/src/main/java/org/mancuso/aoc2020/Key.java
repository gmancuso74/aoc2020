package org.mancuso.aoc2020;

import java.util.HashSet;
import java.util.Set;

public class Key {
    final int x;
    final int y;
    final int z;

    public Key(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean isNeighbor(Key input) {
        int dX = (input.x - this.x);
        int dY = (input.y - this.y);
        int dZ = (input.z - this.z);
        return (dX < 2 && dX > -2) && (dY < 2 && dY > -2) && (dZ < 2 && dZ > -2);
    }

    public Set<Key> neighbors() {
        Set<Key> result = new HashSet<>();
        for (int k = z - 1; k < z + 2; k++)
            for (int j = y - 1; j < y + 2; j++)
                for (int i = x - 1; i < x + 2; i++)
                    result.add(new Key(i, j, k));
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

        final Key other = (Key) obj;
        if (this.x == other.x && this.y == other.y && this.z == other.z) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 5 * x + 7 * y + 13 * z;
    }

    @Override
    public String toString() {
        return String.format("[%d,%d,%d]", x, y, z);
    }

}