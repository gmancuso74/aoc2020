package org.mancuso.aoc2020;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Day18Test {
    @Test
    public void testTokenize() {
        Day18 day = new Day18();
        List<String> tokens = day.tokenize("1 + (2 + 3) + 4");
        assertEquals(9, tokens.size());

    }

    @Test
    public void testParseSimple() {
        String input = "1 + 2 * 3 + 4 * 5 + 6";
        Day18 day = new Day18();
        List<String> tokens = day.tokenize(input);
        assertEquals(11, tokens.size());
        assertEquals("1", tokens.get(0));
        assertEquals("+", tokens.get(5));
        TreeNode root = day.processNode(tokens, null);
        assertEquals(Integer.valueOf(1), root.getLeft().getValue());
        assertEquals(Operation.Add, root.getOperation());
    }

    @Test
    public void testParseSingleParens() {
        String input = "1 + ( 2 + 3 ) + 4";
        Day18 day = new Day18();
        var tokens = day.tokenize(input);
        assertEquals(9, tokens.size());
        TreeNode root = day.processNode(tokens, null);
        String[] expected = new String[]{"1", "+", "+", "4"};
        assertEquals(Integer.valueOf(1), root.getLeft().getValue());
        assertEquals(Operation.Add, root.getOperation());
    }

}
