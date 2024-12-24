package prohladenn.vko._template;

import java.util.List;
import java.util.Scanner;

public final class Inputs {

    private Inputs() {
    }

    public record InputLine(String line) {
        public static InputLine fromSc(Scanner sc) {
            return new InputLine(sc.nextLine());
        }
    }

    public record InputLines(List<String> lines) {
        public static InputLines fromSc(Scanner sc) {
            return new InputLines(sc.tokens().toList());
        }
    }

    public record InputMatrix(int[][] matrix, int rows, int cols) {
        public static InputMatrix fromSc(Scanner sc) {
            var rows = sc.tokens().map(Integer::parseInt).toList();
            var matrix = new int[rows.size()][];
            for (int i = 0; i < rows.size(); i++) matrix[i] = new int[rows.get(i)];
            return new InputMatrix(matrix, matrix.length, matrix[0].length);
        }
    }
}
