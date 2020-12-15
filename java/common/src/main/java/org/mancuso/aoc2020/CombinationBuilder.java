package org.mancuso.aoc2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationBuilder<T> {
	/**
	 * modified from:
	 * https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
	 * 
	 * @param input Input list
	 * @param data  NTuple to store current combination
	 * @param start Starting index in input list
	 * @param end   Ending index in input list
	 * @param index Current index in data ("depth")
	 * @param r     Size of the combination being calculated (the N in NTuple)
	 */
	private void combinationUtil(List<T> input, NTuple<T> data, int start, int end, int index, int r,List<NTuple<T>> combinations) {
		if (data == null) {
			data = new NTuple<>(r);
		} else {
			data = new NTuple<>(data);
		}
		// Current combination is ready to be printed, print it
		if (index == r) {
			combinations.add(data);
			return;
		}

		// replace index with all possible elements. The condition
		// "end-i+1 >= r-index" makes sure that including one element
		// at index will make a combination with remaining elements
		// at remaining positions
		for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
			data.set(index, input.get(i));
			combinationUtil(input, data, i + 1, end, index + 1, r,combinations);
		}
	}
	
	public List<NTuple<T>> getCombinations(List<T> input, int r) {
		List<NTuple<T>> combinations = new ArrayList<>();
		combinationUtil(input, null, 0, input.size() - 1, 0, r,combinations);
		return combinations;
	}


	/* Driver function to check for above function */
	public static void main(String[] args) {
		List<Integer> input = Arrays.asList(1, 2, 3, 4, 5);
		CombinationBuilder<Integer> lc = new CombinationBuilder<>();
		List<NTuple<Integer>> result = lc.getCombinations(input, 2);
		for (NTuple<Integer> nTuple : result) {
			nTuple.print(System.out);
		}
		System.out.println("===========================");
		result=lc.getCombinations(input, 3);
		for (NTuple<Integer> nTuple : result) {
			nTuple.print(System.out);
		}

	}
}
