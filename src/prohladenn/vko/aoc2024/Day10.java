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
        int ans = 0;
        boolean[][] visited = new boolean[input.rows()][input.cols()];
        for (int i = 0; i < input.rows(); i++) {
            for (int j = 0; j < input.cols(); j++) {
                if (input.matrix()[i][j] == '0') {
                    ans += pathCount(input.matrix(), visited, i, j, trackVisited);
                }
            }
        }
        return ans;
    }

    private int pathCount(char[][] matrix, boolean[][] visited, int i, int j, boolean trackVisited) {
        if (trackVisited && visited[i][j]) return 0;
        if (trackVisited) visited[i][j] = true;

        var current = matrix[i][j];
        if (current == '9') return 1;

        int ans = 0;
        if (i > 0 && matrix[i - 1][j] - current == 1)
            ans += pathCount(matrix, visited, i - 1, j, trackVisited);
        if (j > 0 && matrix[i][j - 1] - current == 1)
            ans += pathCount(matrix, visited, i, j - 1, trackVisited);
        if (i < matrix.length - 1 && matrix[i + 1][j] - current == 1)
            ans += pathCount(matrix, visited, i + 1, j, trackVisited);
        if (j < matrix[0].length - 1 && matrix[i][j + 1] - current == 1)
            ans += pathCount(matrix, visited, i, j + 1, trackVisited);

        if (trackVisited) visited[i][j] = false;
        return ans;
    }
}