package prohladenn.vko.aoc2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Day02 extends AbstractDay<Day02.Input> {

    protected record Input(List<List<Integer>> reportList) {
    }

    public static void main(String[] args) {
        new Day02().run();
    }

    @Override
    protected String getInputFileName() {
        return "day02.txt";
    }

    @Override
    protected Day02.Input readInput(Scanner sc) {
        var reportList = new ArrayList<List<Integer>>(1000);

        while (sc.hasNextLine()) {
            var line = sc.nextLine();
            var report = Arrays.stream(line.split(" "))
                    .map(Integer::parseInt)
                    .toList();
            reportList.add(report);
        }

        return new Input(reportList);
    }

    @Override
    protected Object solvePart1(Day02.Input input) {
        return input.reportList().stream()
                .mapToInt(report -> isSafe(report) ? 1 : 0)
                .sum();
    }

    @Override
    protected Object solvePart2(Day02.Input input) {
        return input.reportList().stream()
                .mapToInt(report -> IntStream.range(0, report.size())
                        .anyMatch(i -> {
                            var copy = new ArrayList<>(report);
                            copy.remove(i);
                            return isSafe(copy);
                        }) ? 1 : 0)
                .sum();
    }

    private boolean isSafe(List<Integer> report) {
        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 0; i < report.size() - 1; i++) {
            int diff = report.get(i + 1) - report.get(i);
            increasing &= diff >= 1 && diff <= 3;
            decreasing &= diff <= -1 && diff >= -3;
        }

        return increasing || decreasing;
    }
}
