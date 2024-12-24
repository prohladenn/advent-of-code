package prohladenn.vko._template;

import java.io.InputStream;
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
            var readStart = System.currentTimeMillis();
            T input = readInput(sc);
            var readDuration = System.currentTimeMillis() - readStart;
            System.out.println("Read Input Duration: " + readDuration + " ms\n");

            var solve1Start = System.currentTimeMillis();
            System.out.println("Part 1: " + solvePart1(input));
            var solve1Duration = System.currentTimeMillis() - solve1Start;
            System.out.println("Solve Part 1 Duration: " + solve1Duration + " ms\n");

            var solve2Start = System.currentTimeMillis();
            System.out.println("Part 2: " + solvePart2(input));
            var solve2Duration = System.currentTimeMillis() - solve2Start;
            System.out.println("Solve Part 2 Duration: " + solve2Duration + " ms\n");
        } catch (Exception e) {
            System.err.print("An error occurred: ");
            e.printStackTrace(System.err);
        } finally {
            System.out.println("Total Duration: " + (System.currentTimeMillis() - start) + " ms");
        }
    }

    public record Pair<T, U>(T left, U right) {
        public static <T, U> Pair<T, U> of(T left, U right) {
            return new Pair<>(left, right);
        }

        public T left() {
            return left;
        }

        public U right() {
            return right;
        }
    }
}
