package prohladenn.vko.aoc2024;

import prohladenn.vko._template.AbstractDay;
import prohladenn.vko._template.Inputs.InputLines;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class Day03 extends AbstractDay<InputLines> {

    public static void main(String[] args) {
        new Day03().run();
    }

    @Override
    protected String getInputFileName() {
        return "day03.txt";
    }

    @Override
    protected InputLines readInput(Scanner sc) {
        return InputLines.fromSc(sc);
    }

    @Override
    protected Object solvePart1(InputLines input) {
        var regex = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        return input.lines().stream()
                .flatMap(line -> regex.matcher(line).results())
                .mapToLong(m -> Long.parseLong(m.group(1)) * Long.parseLong(m.group(2)))
                .sum();
    }

    @Override
    protected Object solvePart2(InputLines input) {
        var regex = Pattern.compile("(do\\(\\)|don't\\(\\)|mul\\((\\d{1,3}),(\\d{1,3})\\))");
        var enabled = new AtomicBoolean(true);
        return input.lines().stream()
                .flatMap(line -> regex.matcher(line).results())
                .mapToLong(m -> {
                    if (m.group(1).equals("do()")) {
                        enabled.set(true);
                    } else if (m.group(1).equals("don't()")) {
                        enabled.set(false);
                    } else if (enabled.get()) {
                        return Long.parseLong(m.group(2)) * Long.parseLong(m.group(3));
                    }
                    return 0;
                })
                .sum();
    }
}
