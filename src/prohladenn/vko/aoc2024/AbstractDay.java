package prohladenn.vko.aoc2024;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public abstract class AbstractDay<T> {

    protected abstract String getInputFileName();

    protected abstract T readInput(Scanner sc);

    protected abstract Object solvePart1(T input);

    protected abstract Object solvePart2(T input);

    protected void run() {
        var start = System.currentTimeMillis();
        InputStream inputStream = getClass().getResourceAsStream(getInputFileName());
        if (inputStream == null) {
            System.err.println("Input file not found: " + getInputFileName());
            return;
        }

        try (var sc = new Scanner(inputStream)) {
            T input = readInput(sc);
            System.out.println("Part 1: " + solvePart1(input));
            System.out.println("Part 2: " + solvePart2(input));
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            System.out.println("Duration: " + (System.currentTimeMillis() - start) + " ms");
        }
    }

    protected record InputLines(List<String> lines) {
        static InputLines fromSc(Scanner sc) {
            return new InputLines(sc.tokens().toList());
        }
    }

    protected record Pair<T, U>(T left, U right) {
        public T getLeft() {
            return left;
        }

        public U getRight() {
            return right;
        }
    }
}
