package org.mancuso.aoc2020;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OldSpace<T> {
    final HashMap<Integer, OldPlane> space = new HashMap<>();
    private int planeSize;
    private T defaultValue;

    public OldSpace(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void init(String[] lines, Function<Character, T> elementConverter) {
        var plane = new OldPlane(lines, elementConverter);
        space.put(0, plane);
        this.planeSize = plane.size;
    }

    public T get(int x, int y, int z) {
        //TODO: create plane z if it doesn't exist yet
        return (T) space.get(z).get(x, y);
    }

    public void set(int x, int y, int z, T value) {
        //TODO create plane z if it doesn't exist yet
        if (!space.containsKey(z)) {
            space.put(z, new OldPlane(planeSize, defaultValue));
        }
        space.get(z).set(x, y, value);
    }

    public String print() {
        String result = new String();
        int highestSpace = 0;

        List<Integer> keys = space.keySet().stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        for (Integer key : keys) {
            result += "Level " + key + System.lineSeparator();
            result += space.get(key).print();
        }
        return result;
    }
}
