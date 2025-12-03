package prohladenn.vko._template;

import prohladenn.vko._template.Inputs.InputLines;

import java.util.Scanner;

public class DayN extends AbstractDay<InputLines> {

    static void main() {
        new DayN().run();
    }

    @Override
    protected String getInputFileName() {
        return "dayN.txt";
    }

    @Override
    protected InputLines readInput(Scanner sc) {
        return InputLines.fromSc(sc);
    }

    @Override
    protected Object solvePart1(InputLines input) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    protected Object solvePart2(InputLines input) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
