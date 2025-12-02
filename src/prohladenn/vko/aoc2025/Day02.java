package prohladenn.vko.aoc2025;

import prohladenn.vko._template.AbstractDay;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Day02 extends AbstractDay<Day02.Input> {

    protected record Input(List<Pair<String, String>> pairs) {
    }

    static void main() {
        new Day02().run();
    }

    @Override
    protected String getInputFileName() {
        return "day02.txt";
    }

    @Override
    protected Input readInput(Scanner sc) {
        return new Input(Arrays.stream(sc.nextLine().split(","))
                .map(s -> s.split("-"))
                .map(arr -> new Pair<>(arr[0].trim(), arr[1].trim()))
                .toList());
    }

    @Override
    protected Object solvePart1(Input input) {
        long sum = 0;

        for (var pair : input.pairs()) {
            long start = Long.parseLong(pair.left());
            long end = Long.parseLong(pair.right());

            for (long id = start; id <= end; id++) {
                var sid = String.valueOf(id);

                if (sid.length() % 2 != 0) continue;
                String firstHalf = sid.substring(0, sid.length() / 2);
                String secondHalf = sid.substring(sid.length() / 2);
                if (firstHalf.equals(secondHalf)) sum += id;
            }
        }

        return sum;
    }

    @Override
    protected Object solvePart2(Input input) {
        long sum = 0;

        for (var pair : input.pairs()) {
            long start = Long.parseLong(pair.left());
            long end = Long.parseLong(pair.right());
            var uniqueIds = new HashSet<Long>();

            for (long id = start; id <= end; id++) {
                var sid = String.valueOf(id);

                for (int patternLen = 1; patternLen <= sid.length() / 2; patternLen++) {
                    if (sid.length() % patternLen != 0) continue;
                    var pattern = sid.substring(0, patternLen);
                    var invalidId = pattern.repeat(sid.length() / patternLen);
                    if (sid.equals(invalidId)) uniqueIds.add(id);
                }
            }

            sum += uniqueIds.stream().mapToLong(Long::longValue).sum();
        }

        return sum;
    }
}