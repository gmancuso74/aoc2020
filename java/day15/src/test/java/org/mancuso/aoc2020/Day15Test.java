package org.mancuso.aoc2020;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day15Test {
    Day15 day = new Day15();
    List<Long> input= Arrays.asList(new Long[]{0L,3L,6L});

    @Test
    public void testPart1Init() throws Exception {
        day.part1Init(input);
        assertEquals(6L, day.spoken.longValue());
        assertEquals(2, day.memory.size());
        assertEquals(4L,day.turn.longValue());
    }

    private void verify(List<Long> input, int finalTurn, int expected) {
        day.part1Init(input);
        Long result = day.part1Run(finalTurn);
        assertEquals(String.format("For turn %d, expected %d but got %d",finalTurn,expected,result),expected,result.longValue());
    }

    @Test
    public void testSmall() throws Exception {
        day.part1Init(input);
        int[] expected = new int[]{0,0,3,6,0,3,3,1,0,4,0};
        for(int i=4;i<=10;i++){
            verify(input, i, expected[i]);
        }
        verify(input, 2020, 436);
        verify(Arrays.asList(new Long[]{1L,3L,2L}), 2020, 1);
        verify(Arrays.asList(new Long[]{2L,1L,3L}), 2020, 10);
        verify(Arrays.asList(new Long[]{1L,2L,3L}), 2020, 27);
        verify(Arrays.asList(new Long[]{2L,3L,1L}), 2020, 78);
        verify(Arrays.asList(new Long[]{3L,2L,1L}), 2020, 438);
        verify(Arrays.asList(new Long[]{3L,1L,2L}), 2020, 1836);
    }

    @Test public void testPart2() {
        verify(Arrays.asList(new Long[]{0L,3L,6L}), 30000000, 175594);
//        verify(Arrays.asList(new Long[]{1L,3L,2L}), 30000000, 2578);
//        verify(Arrays.asList(new Long[]{2L,1L,3L}), 30000000, 3544142);
//        verify(Arrays.asList(new Long[]{1L,2L,3L}), 30000000, 261214);
//        verify(Arrays.asList(new Long[]{2L,3L,1L}), 30000000, 6895259);
//        verify(Arrays.asList(new Long[]{3L,2L,1L}), 30000000, 18);
//        verify(Arrays.asList(new Long[]{3L,1L,2L}), 30000000, 362);

    }
}
