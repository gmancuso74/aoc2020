package org.mancuso.aoc2020;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Day14Test {
    Day14 day = new Day14();

    private void verify(String mask, String input, String expected) {
        BitSet result = day.apply(mask.toCharArray(), day.fromString(input));
        assertEquals(expected, day.toBitString(result));
    }

    @Test public void testConversions() {
        String inStr="000000000000000000000000000000001011";
        BitSet in = day.fromString(inStr);
        String outStr=day.toBitString(in);
        assertEquals(inStr,outStr);
    }

    @Test
    public void testMask() {
        char[] maskStr = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X".toCharArray();
        var mask = day.parseMask(maskStr);
        assertEquals(2, mask.size());
        assertTrue(mask.stream().anyMatch(x -> x.a == 1 && x.b == Boolean.FALSE));
        assertTrue(mask.stream().anyMatch(x -> x.a == 6 && x.b == Boolean.TRUE));
    }

    @Test
    public void verifyExamples() {
        verify("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X", "000000000000000000000000000000001011", "000000000000000000000000000001001001");
        verify("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X", "000000000000000000000000000001100101", "000000000000000000000000000001100101");
        verify("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X", "000000000000000000000000000000000000", "000000000000000000000000000001000000");
    }

    @Test
    public void testApply() {
        char[] mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X".toCharArray();
        long val = Long.parseLong("000000000000000000000000000000001011", 2);
        assertEquals(11L, val);
        BitSet value = BitSet.valueOf(new long[]{val});
        assertTrue(value.size() >= 36);
        BitSet resultBS = day.apply(mask, value);
        long[] result = resultBS.toLongArray();
        assertEquals(1, result.length);
        assertEquals(73L, result[0]);

        value = day.fromString("000000000000000000000000000001100101");
        result = value.toLongArray();
        assertEquals(1, result.length);
        assertEquals(101L, result[0]);

        resultBS = day.apply(mask, value);
        result = resultBS.toLongArray();
        assertEquals(1, result.length);
        assertEquals(101L, result[0]);
    }

    @Test
    public void testSmall() throws Exception {
        Path p = Paths.get("small");
        List<String> lines = Files.readAllLines(p);
        day.run(lines);
        assertEquals(165L,day.getSum());
    }
    @Test
    public void testSmallPart2() throws Exception {
        Path p = Paths.get("small2");
        List<String> lines = Files.readAllLines(p);
        day.runPart2(lines);
        assertEquals(208L,day.getSum());
    }

    @Test public void testMemoryPutStillHashes() {
        Long l1 = Long.parseLong("2000302020202");
        Long l2 = Long.parseLong("2000302020202");
        day.memory.put(l1,33L);
        day.memory.put(l2,44L);

        Set<Long> keys=day.memory.keySet();
        assertEquals(1,keys.size());
        assertEquals(44L,day.memory.get(l1).longValue());
    }

    @Test public void testAddrDecode()  {
        List<Long> result = day.decodeMask("0X0X");
        assertEquals(4,result.size());
        assertTrue(result.contains(0L));
        assertTrue(result.contains(1L));
        assertTrue(result.contains(4L));
        assertTrue(result.contains(5L));
    }

}
