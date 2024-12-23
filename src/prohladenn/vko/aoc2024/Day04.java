package prohladenn.vko.aoc2024;

import prohladenn.vko._template.AbstractDay;

import java.util.ArrayList;
import java.util.Scanner;

public class Day04 extends AbstractDay<Day04.Input> {

    protected record Input(char[][] matrix) {
    }

    public static void main(String[] args) {
        new Day04().run();
    }

    @Override
    protected String getInputFileName() {
        return "day04.txt";
    }

    @Override
    protected Input readInput(Scanner sc) {
        var rows = new ArrayList<char[]>(150);

        while (sc.hasNextLine()) {
            rows.add(sc.nextLine().toCharArray());
        }

        return new Input(rows.toArray(new char[0][]));
    }

    @Override
    protected Object solvePart1(Input input) {
        var matrix = input.matrix();
        int ans = 0;

        for (char[] row : matrix) {
            ans += checkSequence(row);
        }

        for (int j = 0; j < matrix[0].length; j++) {
            char[] column = new char[matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                column[i] = matrix[i][j];
            }
            ans += checkSequence(column);
        }

        for (int i = 0; i < matrix.length - 3; i++) {
            for (int j = 0; j < matrix[i].length - 3; j++) {
                char[] diagonal1 = {matrix[i][j], matrix[i + 1][j + 1], matrix[i + 2][j + 2], matrix[i + 3][j + 3]};
                char[] diagonal2 = {matrix[i][j + 3], matrix[i + 1][j + 2], matrix[i + 2][j + 1], matrix[i + 3][j]};
                ans += checkSequence(diagonal1);
                ans += checkSequence(diagonal2);
            }
        }

        return ans;
    }

    private int checkSequence(char[] sequence) {
        int count = 0;
        for (int j = 0; j < sequence.length - 3; j++) {
            if (sequence[j] == 'X' && sequence[j + 1] == 'M' && sequence[j + 2] == 'A' && sequence[j + 3] == 'S') {
                count++;
            }
            if (sequence[j] == 'S' && sequence[j + 1] == 'A' && sequence[j + 2] == 'M' && sequence[j + 3] == 'X') {
                count++;
            }
        }
        return count;
    }

    @Override
    protected Object solvePart2(Input input) {
        var matrix = input.matrix();
        int ans = 0;

        for (int i = 1; i < matrix.length - 1; i++) {
            for (int j = 1; j < matrix[i].length - 1; j++) {
                if (matrix[i][j] == 'A' && isMatchingPattern(matrix, i, j)) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private boolean isMatchingPattern(char[][] matrix, int i, int j) {
        char topLeft = matrix[i - 1][j - 1];
        char topRight = matrix[i - 1][j + 1];
        char bottomLeft = matrix[i + 1][j - 1];
        char bottomRight = matrix[i + 1][j + 1];

        return (topLeft == 'M' && topRight == 'M' && bottomLeft == 'S' && bottomRight == 'S') ||
                (topLeft == 'S' && topRight == 'S' && bottomLeft == 'M' && bottomRight == 'M') ||
                (topLeft == 'M' && topRight == 'S' && bottomLeft == 'M' && bottomRight == 'S') ||
                (topLeft == 'S' && topRight == 'M' && bottomLeft == 'S' && bottomRight == 'M');
    }
}