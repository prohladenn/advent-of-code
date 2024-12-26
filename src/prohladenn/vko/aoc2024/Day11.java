package prohladenn.vko.aoc2024;

import prohladenn.vko._template.AbstractDay;
import prohladenn.vko._template.Inputs.InputLine;

import java.math.BigInteger;
import java.util.*;

public class Day11 extends AbstractDay<InputLine> {

    private final Map<String, Map<Integer, Long>> memo = new HashMap<>();

    public static void main(String[] args) {
        new Day11().run();
    }

    @Override
    protected String getInputFileName() {
        return "day11.txt";
    }

    @Override
    protected InputLine readInput(Scanner sc) {
        return InputLine.fromSc(sc);
    }

    @Override
    protected Object solvePart1(InputLine input) {
        return solve(input, 25);
    }

    @Override
    protected Object solvePart2(InputLine input) {
        return solve(input, 75);
    }

    private Object solve(InputLine input, int t) {
        List<String> stones = Arrays.stream(input.line().split(" "))
                .map(String::trim)
                .toList();

        long totalStones = 0;
        for (String stone : stones) {
            totalStones += countStones(stone, t);
        }

        return totalStones;
    }

    private long countStones(String stone, int t) {
        if (t == 0) return 1;

        memo.putIfAbsent(stone, new HashMap<>());
        if (memo.get(stone).containsKey(t)) {
            return memo.get(stone).get(t);
        }

        List<String> nextStones = transform(stone);
        long total = 0;
        for (String nextStone : nextStones) {
            total += countStones(nextStone, t - 1);
        }

        memo.get(stone).put(t, total);
        return total;
    }

    private List<String> transform(String stone) {
        if (stone.equals("0")) {
            return List.of("1");
        }

        int length = stone.length();

        if (length % 2 == 0) {
            int mid = length / 2;
            String left = removeLeadingZeros(stone.substring(0, mid));
            String right = removeLeadingZeros(stone.substring(mid));
            return List.of(left.isEmpty() ? "0" : left, right.isEmpty() ? "0" : right);
        }

        BigInteger value = new BigInteger(stone);
        value = value.multiply(BigInteger.valueOf(2024));
        return List.of(value.toString());
    }

    private String removeLeadingZeros(String s) {
        return s.replaceFirst("^0+", "");
    }
}
