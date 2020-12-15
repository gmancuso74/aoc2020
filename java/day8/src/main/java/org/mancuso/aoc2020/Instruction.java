package org.mancuso.aoc2020;

public class Instruction {
    Operation operation;
    String argument;
    public Integer execute(Integer lineNum){
        return operation.operation.apply(argument,lineNum);
    }
}
