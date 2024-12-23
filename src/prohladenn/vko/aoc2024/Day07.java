package prohladenn.vko.aoc2024;

import prohladenn.vko._template.AbstractDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day07 extends AbstractDay<Day07.Input> {

    protected record Input(List<Pair<Long, List<Integer>>> line) {
    }

    public static void main(String[] args) {
        new Day07().run();
    }

    @Override
    protected String getInputFileName() {
        return "day07.txt";
    }

    @Override
    protected Input readInput(Scanner sc) {
        var data = new ArrayList<Pair<Long, List<Integer>>>();

        while (sc.hasNextLine()) {
            var line = sc.nextLine().split(":");
            var res = Long.parseLong(line[0]);
            var arr = Arrays.stream(line[1].trim().split(" "))
                    .map(Integer::parseInt)
                    .toList();
            data.add(new Pair<>(res, arr));
        }

        return new Input(data);
    }

    @Override
    protected Object solvePart1(Input input) {
        return input.line.stream()
                .filter(p -> calculate(p.right().getFirst(), p.left(), 1, p.right(), false))
                .mapToLong(Pair::left)
                .sum();
    }

    @Override
    protected Object solvePart2(Input input) {
        return input.line.stream()
                .filter(p -> calculate(p.right().getFirst(), p.left(), 1, p.right(), true))
                .mapToLong(Pair::left)
                .sum();
    }

    private boolean calculate(long actual, long expected, int pos, List<Integer> list, boolean isConcatAllowed) {
        if (pos == list.size()) {
            return actual == expected;
        }
        return calculate(actual + list.get(pos), expected, pos + 1, list, isConcatAllowed)
                || calculate(actual * list.get(pos), expected, pos + 1, list, isConcatAllowed)
                || (isConcatAllowed && calculate(concat(actual, list.get(pos)), expected, pos + 1, list, true));
    }

    private long concat(long a, long b) {
        return Long.parseLong(a + "" + b);
    }
}