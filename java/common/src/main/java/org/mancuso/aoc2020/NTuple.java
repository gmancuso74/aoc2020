package org.mancuso.aoc2020;

import java.io.PrintStream;

public class NTuple<T> {
	private T[] data; // due to type-safety reasons, don't be passing this around

	@SuppressWarnings("unchecked")
	public NTuple(int n) {
		data = (T[]) new Object[n];
	}
	
	@SuppressWarnings("unchecked")
	public NTuple (NTuple<T> copy) {
		data = (T[]) new Object[copy.getSize()];
		for(int i=0;i<copy.getSize();i++) {
			data[i]=copy.get(i);
		}
	}

	public int getSize() {
		return (data.length);
	}

	public T get(int idx) {
		return data[idx];
	}

	public void set(int idx, T value) {
		data[idx] = value;
	}

	public void print(PrintStream out) {
		out.print("{");
		for (int i = 0; i < data.length; i++) {
			out.print(data[i]);
			if (i + 1 != data.length) {
				out.print("\t");
			}
		}
		out.println("}");
	}
}
