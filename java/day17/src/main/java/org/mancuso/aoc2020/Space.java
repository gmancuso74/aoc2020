package org.mancuso.aoc2020;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Space<T> {
    Map<Key, MapElement<T>> space = new HashMap<>();
    Map<Key4D, MapElement<T>> space4d = new HashMap<>();

    public Map<Key4D, MapElement<T>> convertTo4D(Map<Key, MapElement<T>> input) {
        Map<Key4D, MapElement<T>> result = new HashMap<>();
        for (Key key : input.keySet()) {
            result.put(new Key4D(key), input.get(key));
        }
        return result;
    }

    public Map<Key, MapElement<T>> parseMap(List<String> lines, Function<Character, T> elementConverter, T expectedValue) {
        Map<Key, MapElement<T>> map = new HashMap<>();
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (elementConverter.apply(line.charAt(x)).equals(expectedValue)) {
                    map.put(new Key(x, lines.size() - y - 1, 0), new MapElement<>(expectedValue));
                }
            }
        }
        return map;
    }

    public void init(List<String> lines, Function<Character, T> elementConverter, T expectedValue) {
        space = parseMap(lines, elementConverter, expectedValue);
        space4d = convertTo4D(space);
    }

    public Map<Key4D, Integer> calculate4DNeighborCount(Map<Key4D, MapElement<T>> localSpace) {
        Map<Key4D, Integer> result = new HashMap<>();
        for (Key4D key : localSpace.keySet()) {
            for (Key4D neighbor : key.neighbors()) {
                if (result.containsKey(neighbor)) {
                    Integer count = result.get(neighbor);
                    result.put(neighbor, count + 1);
                } else {
                    result.put(neighbor, 1);
                }
            }
        }
        return result;
    }

    public Map<Key, Integer> calculateNeighborCount(Map<Key, MapElement<T>> localSpace) {
        Map<Key, Integer> result = new HashMap<>();
        for (Key key : localSpace.keySet()) {
            for (Key neighbor : key.neighbors()) {
                if (result.containsKey(neighbor)) {
                    Integer count = result.get(neighbor);
                    result.put(neighbor, count + 1);
                } else {
                    result.put(neighbor, 1);
                }
            }
        }
        return result;
    }

    public Map<Key, MapElement<T>> part1cycle() {
        Map<Key, MapElement<T>> result = new HashMap<>();
        var counts = calculateNeighborCount(this.space);
        for (Key currentKey : counts.keySet()) {
            int neighborCount = counts.get(currentKey);
            Status currentStatus = (Status) space.getOrDefault(currentKey, new MapElement(Status.INACTIVE)).get();
            if (currentStatus == Status.ACTIVE) {
                if (neighborCount == 2 || neighborCount == 3) {
                    result.put(currentKey, new MapElement(Status.ACTIVE));
                }
            } else {
                if (neighborCount == 3) {
                    result.put(currentKey, new MapElement(Status.ACTIVE));
                }
            }
        }
        space = result;
        return result;
    }

    public Map<Key4D, MapElement<T>> part2cycle() {
        Map<Key4D, MapElement<T>> result = new HashMap<>();
        var counts = calculate4DNeighborCount(this.space4d);
        //check neighborcount for 2,0,0,0 --> should be 2
        for (Key4D currentKey : counts.keySet()) {
            int neighborCount = counts.get(currentKey);
            Status currentStatus = (Status) space4d.getOrDefault(currentKey, new MapElement(Status.INACTIVE)).get();
            if (currentStatus == Status.ACTIVE) {
                if (neighborCount == 2 || neighborCount == 3) {
                    result.put(currentKey, new MapElement(Status.ACTIVE));
                }
            } else {
                if (neighborCount == 3) {
                    result.put(currentKey, new MapElement(Status.ACTIVE));
                }
            }
        }
        space4d = result;
        return result;
    }
}
