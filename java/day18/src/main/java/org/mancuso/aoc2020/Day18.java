package org.mancuso.aoc2020;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mancuso.aoc2020.Operation.*;

public class Day18 extends AbstractDay {
    @Override
    public String name() {
        return "Day18";
    }

    @Override
    public String part1() {
        return null;
    }

    @Override
    public String part2() {
        return null;
    }

    public Optional<Integer> tryParse(String text) {
        Optional<Integer> result = Optional.empty();
        try {
            result = Optional.of(Integer.parseInt(text));
        } catch (NumberFormatException e) {
            //why can't we just have a tryParse
            //or at least give us the regex
        }
        return result;
    }

    //returns index of last processed node in input
    TreeNode processNode(List<String> input, TreeNode context) {
        String token = input.get(0);
        TreeNode node = new Part1Node();

        switch (token) {
            case ")":
                return context;
            case "(":
                node.setOperation(Parens);
                node.setLeft(processNode(input.subList(1, input.size()), node));
                if (context == null) {
                    context = new Part1Node();
                }
                if (context.getLeft() == null) {
                    context.setLeft(node);
                } else {
                    context.setRight(node);
                }
                break;
            case "+":
                node.setOperation(Add);
                node.setLeft(context);
                node.setRight(processNode(input.subList(1, input.size()), node));
                break;
            case "-":
                node.setOperation(Subtract);
                node.setLeft(context);
                node.setRight(processNode(input.subList(1, input.size()), node));
                break;
            case "*":
                node.setOperation(Multiply);
                node.setLeft(context);
                node.setRight(processNode(input.subList(1, input.size()), node));
                break;
            default: //should be a number now
                var optInt = tryParse(token);
                if (optInt.isPresent()) {
                    Integer value = optInt.get();
                    node = new Part1Node(value);
                    if (context == null) {
                        context = node;
                    } else if (context.getLeft() == null) {
                        context.setLeft(node);
                    } else {
                        context.setRight(node);
                    }
                } else {
                    System.out.println("Warning; unhandled input: " + token);
                }
                break;
        }
        return node;
    }

    List<String> tokenize(String input) {
        String filteredInput = input.replaceAll("\\(", "( ").replaceAll("\\)", " )");
        String[] tokens = filteredInput.split("\\h+");
        return Arrays.asList(tokens);
    }
}
