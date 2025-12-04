package prohladenn.vko.aoc2025;

import prohladenn.vko._template.AbstractDay;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Day04 extends AbstractDay<Day04.Input> {

    protected record Input(char[][] matrix) {
    }

    static void main() {
        new Day04().run();
    }

    @Override
    protected String getInputFileName() {
        return "day04.txt";
    }

    @Override
    protected Input readInput(Scanner sc) {
        var rows = new ArrayDeque<char[]>();

        while (sc.hasNextLine()) {
            var chars = sc.nextLine().toCharArray();
            var wrapped = new char[chars.length + 2];
            wrapped[0] = '.';
            System.arraycopy(chars, 0, wrapped, 1, chars.length);
            wrapped[wrapped.length - 1] = '.';
            rows.add(wrapped);
        }

        var emptyRow = new char[rows.getFirst().length];
        Arrays.fill(emptyRow, '.');
        rows.addFirst(emptyRow);
        rows.addLast(emptyRow);

        return new Input(rows.toArray(new char[0][]));
    }

    @Override
    protected Object solvePart1(Input input) {
        return iterate(input.matrix(), false);
    }

    @Override
    protected Object solvePart2(Input input) {
        int count = 0, removed = -1;

        while (removed != 0) {
            removed = iterate(input.matrix(), true);
            count += removed;
        }

        return count;
    }

    private int iterate(char[][] matrix, boolean update) {
        int count = 0;
        for (int i = 1; i < matrix.length - 1; i++) {
            for (int j = 1; j < matrix[i].length - 1; j++) {
                if (matrix[i][j] == '@' && countRollsAround(matrix, i, j) < 4) {
                    count++;
                    if (update) matrix[i][j] = '.';
                }
            }
        }
        return count;
    }

    private int countRollsAround(char[][] matrix, int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i == row && j == col) continue;
                if (matrix[i][j] == '@') {
                    count++;
                }
            }
        }
        return count;
    }
}
