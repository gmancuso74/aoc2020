package org.mancuso.aoc2020;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OldPlane<T> {
    private final MapElement<T>[][] plane;
    public final int size;

    //Build a plane of size 'size', with all elements initialized to 'value'
    public OldPlane(int size, T value) {
        this.size = size;
        plane = new MapElement[size][size];
        for (int y = size - 1; y >= 0; y--) {
            for (int x = 0; x < size; x++) {
                plane[y][x] = new MapElement<>(value);
            }
        }
    }

    public OldPlane(String[] lines, Function<Character, T> elementConverter) {
        //we're assuming x=y; seems like that's a valid assumption
        size = lines.length;
        plane = new MapElement[size][size];
        for (int y = size - 1; y >= 0; y--) {
            String line = lines[y];
            for (int x = 0; x < size; x++) {
                Character c = line.charAt(x);
                MapElement<T> element = new MapElement<>(elementConverter.apply(c));
                plane[y][x] = element;
            }
        }
    }

    public T get(int x, int y) {
        return plane[y][x].get();
    }

    public void set(int x, int y, T value) {
        plane[y][x].set(value);
    }

    public String print() {
        String result = new String();
        for (int y = size - 1; y >= 0; y--) {
            String row = Arrays.stream(plane[y]).map(MapElement::print).collect(Collectors.joining());
            result += row + System.lineSeparator();
        }
        return result;
    }
}
