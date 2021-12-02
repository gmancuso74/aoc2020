package org.mancuso.aoc2020;

public class MapElement<T> {
    T element;

    public T get() {
        return (T) element;
    }

    public void set(T value) {
        element = value;
    }

    public MapElement() {
    }

    public MapElement(T value) {
        this.set(value);
    }

    public String print() {
        if (Printable.class.isAssignableFrom(element.getClass())) {
            return ((Printable) element).print();
        }
        return element.toString();
    }
}
