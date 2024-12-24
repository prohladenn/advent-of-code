package prohladenn.vko.aoc2024;

import prohladenn.vko._template.AbstractDay;
import prohladenn.vko._template.Inputs.InputMatrix;

import java.util.Scanner;

public class Day10 extends AbstractDay<InputMatrix> {

    public static void main(String[] args) {
        new Day10().run();
    }

    @Override
    protected String getInputFileName() {
        return "day10.txt";
    }

    @Override
    protected InputMatrix readInput(Scanner sc) {
        return InputMatrix.fromSc(sc);
    }

    @Override
    protected Object solvePart1(InputMatrix input) {
        return solve(input, true);
    }

    @Override
    protected Object solvePart2(InputMatrix input) {
        return solve(input, false);
    }

    private Object solve(InputMatrix input, boolean trackVisited) {
        var ans = 0;
        var visited = new boolean[input.rows()][input.cols()];

        for (var i = 0; i < input.rows(); i++) {
            for (var j = 0; j < input.cols(); j++) {
                if (input.matrix()[i][j] == '0') {
                    ans += pathCount(input.matrix(), visited, i, j, trackVisited);
                }
            }
        }

        return ans;
    }

    private int pathCount(char[][] matrix, boolean[][] visited, int row, int col, boolean trackVisited) {
        if (trackVisited && visited[row][col]) {
            return 0;
        }
        if (trackVisited) {
            visited[row][col] = true;
        }

        var current = matrix[row][col];
        if (current == '9') {
            return 1;
        }

        int totalPaths = 0;

        if (row > 0 && matrix[row - 1][col] - current == 1) {
            totalPaths += pathCount(matrix, visited, row - 1, col, trackVisited);
        }
        if (col > 0 && matrix[row][col - 1] - current == 1) {
            totalPaths += pathCount(matrix, visited, row, col - 1, trackVisited);
        }
        if (row < matrix.length - 1 && matrix[row + 1][col] - current == 1) {
            totalPaths += pathCount(matrix, visited, row + 1, col, trackVisited);
        }
        if (col < matrix[0].length - 1 && matrix[row][col + 1] - current == 1) {
            totalPaths += pathCount(matrix, visited, row, col + 1, trackVisited);
        }

        if (trackVisited) {
            visited[row][col] = false;
        }

        return totalPaths;
    }
}