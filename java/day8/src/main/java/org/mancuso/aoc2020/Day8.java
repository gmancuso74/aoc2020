package org.mancuso.aoc2020;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BiFunction;

public class Day8 extends AbstractDay {

    @Override
    public String name() {
        return "Day8";
    }

    public  Map<String,Operation> initPart2(Part2Context context){
        return initPart1(context);
    }

    @SuppressWarnings("CodeBlock2Expr")
    public Map<String,Operation> initPart1(Part1Context context){
        Map<String, Operation> operations = new HashMap<>();
        addInstruction(operations, "nop", (x, y) -> {
            return y + 1;
        });
        addInstruction(operations, "acc", (x, y) -> {
            int arg = Integer.parseInt(x);
            context.acc += arg;
            return y + 1;
        });
        addInstruction(operations, "jmp", (x, y) -> {
            int arg = Integer.parseInt(x);
            return y + arg;
        });
        return operations;
    }

    public void run(List<String> lines, Part1Context ctx) {
        int lineNum = 0;
        Set<Integer> executedLines = new HashSet<>();
        var instSet = initPart1(ctx);
        boolean valid=true;
        while(valid){
            String line = lines.get(lineNum);
            if(executedLines.contains(lineNum)) {
                valid=false;
                continue;
            }
            Instruction ins = parseInstruction(instSet,line);
            executedLines.add(lineNum);
            lineNum=ins.execute(lineNum);
        }
    }

    public Instruction mutate(Instruction ins, Map<String,Operation> instSet) {
        if(ins.operation.code.equals("jmp")) {
            ins.operation= instSet.get("nop");
        } else if(ins.operation.code.equals("nop")) {
            ins.operation=instSet.get("jmp");
        }
        return ins;
    }

    String pretty(Instruction ins) {
        return String.format("%s %s",ins.operation.code,ins.argument);
    }
    public boolean run2(List<String> lines, Part2Context ctx,Map<String,Operation> instSet, int instrToMutate){
        int lineNum = 0;
        Set<Integer> executedLines = new HashSet<>();
        boolean valid=true;
        while(valid){
            String line = lines.get(lineNum);
            if(executedLines.contains(lineNum)) {
                valid=false;
                continue;
            }
            Instruction ins = parseInstruction(instSet,line);
            if(lineNum==instrToMutate) {
                ins=mutate(ins,instSet);
            }
            executedLines.add(lineNum);
            lineNum=ins.execute(lineNum);
            printf("\t\tExecuting: "+ pretty(ins));
            if(lineNum==lines.size()) {
                println("Found success");
                return true;
            }
        }
        return false;
    }

    public int runPart2(List<String> lines, Part2Context ctx) {
        var instSet = initPart2(ctx);
        for(int i=0;i<lines.size();i++) {
            println("Mutating instruction " + i);
            ctx.acc=0;
            boolean success=run2(lines,ctx,instSet,i);
            if(success) {
                return ctx.acc;
            } else {
                println("\tFailed, acc = " + ctx.acc);
            }
        }
        return ctx.acc;
    }

    @Override
    public String part1() {
        Path p = Paths.get("day8/input");
        Part1Context ctx = new Part1Context();
        String result = null;
        try {
            List<String> lines = Files.readAllLines(p);
            run(lines,ctx);
            result=String.valueOf(ctx.acc);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return result;
    }

    private void addInstruction(Map<String, Operation> instructions, String code, BiFunction<String, Integer, Integer> operation) {
        Operation ins = new Operation();
        ins.code = code;
        ins.operation = operation;
        instructions.put(code, ins);
    }

    @Override
    public String part2() {
        Path p = Paths.get("day8/input");
        String result=null;
        try {
            List<String> lines = Files.readAllLines(p);
            Part2Context ctx = new Part2Context();
            result = String.valueOf(runPart2(lines, ctx));
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public Instruction parseInstruction(Map<String,Operation> instrSet, String line) {
        int firstSpaceIdx = line.indexOf(' ');
        String code = line.substring(0,firstSpaceIdx);
        String arg = line.substring(firstSpaceIdx+1);
        if(instrSet.containsKey(code)) {
            Instruction instr = new Instruction();
            instr.operation=instrSet.get(code);
            instr.argument=arg;
            return instr;
        } else {
            throw new RuntimeException("No matching code");
        }
    }
}
