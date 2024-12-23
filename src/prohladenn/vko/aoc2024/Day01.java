package prohladenn.vko.aoc2024;

import prohladenn.vko._template.AbstractDay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day01 extends AbstractDay<Day01.Input> {

    protected record Input(List<Integer> left, List<Integer> right) {
    }

    public static void main(String[] args) {
        new Day01().run();
    }

    @Override
    protected String getInputFileName() {
        return "day01.txt";
    }

    @Override
    protected Day01.Input readInput(Scanner sc) {
        var left = new ArrayList<Integer>(1000);
        var right = new ArrayList<Integer>(1000);

        while (sc.hasNextInt()) {
            left.add(sc.nextInt());
            right.add(sc.nextInt());
        }

        Collections.sort(left);
        Collections.sort(right);

        return new Input(left, right);
    }

    @Override
    protected Object solvePart1(Day01.Input input) {
        return IntStream.range(0, input.left().size())
                .mapToLong(i -> Math.abs(input.left().get(i) - input.right().get(i)))
                .sum();
    }

    @Override
    protected Object solvePart2(Day01.Input input) {
        var rightMap = input.right().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return input.left().stream()
                .mapToLong(i -> i * rightMap.getOrDefault(i, 0L))
                .sum();
    }
}
