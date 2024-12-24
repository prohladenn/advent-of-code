package prohladenn.vko._template;

import java.util.ArrayList;
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

    public record InputMatrix(char[][] matrix, int rows, int cols) {
        public static InputMatrix fromSc(Scanner sc) {
            var rows = new ArrayList<char[]>();
            while (sc.hasNextLine()) rows.add(sc.nextLine().toCharArray());
            var matrix = rows.toArray(new char[0][]);
            return new InputMatrix(matrix, matrix.length, matrix[0].length);
        }
    }
}
