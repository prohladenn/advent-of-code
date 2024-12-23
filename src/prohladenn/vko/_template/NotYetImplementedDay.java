package prohladenn.vko._template;

import java.util.Scanner;

public class NotYetImplementedDay extends AbstractDay<AbstractDay.InputLines> {

    public static void main(String[] args) {
        new NotYetImplementedDay().run();
    }

    @Override
    protected String getInputFileName() {
        return "FILL_ME.txt";
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
