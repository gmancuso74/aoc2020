package org.mancuso.aoc2020;

public class Command {
    public final CommandType command;
    public final Integer value;

    public Command(String input) {
        String cmd = input.substring(0, 1);
        String valueStr = input.substring(1);
        this.command = CommandType.valueOf(cmd);
        this.value = Integer.parseInt(valueStr);
    }

    public Command(CommandType ct, Integer value) {
        this.command = ct;
        this.value = value;
    }

}
