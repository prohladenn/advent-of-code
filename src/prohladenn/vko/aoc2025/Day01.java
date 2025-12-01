package prohladenn.vko.aoc2025;

import prohladenn.vko._template.AbstractDay;
import prohladenn.vko._template.Inputs.InputLines;

import java.util.Scanner;

public class Day01 extends AbstractDay<InputLines> {

    public static void main(String[] args) {
        new Day01().run();
    }

    @Override
    protected String getInputFileName() {
        return "day01.txt";
    }

    @Override
    protected InputLines readInput(Scanner sc) {
        return InputLines.fromSc(sc);
    }

    @Override
    protected Object solvePart1(InputLines input) {
        int position = 50, count = 0;
        for (String line : input.lines()) {
            int direction = line.charAt(0) == 'R' ? 1 : -1;
            int steps = Integer.parseInt(line.substring(1));

            position = Math.floorMod(position + direction * steps, 100);
            if (position == 0) count++;
        }
        return count;
    }

    @Override
    protected Object solvePart2(InputLines input) {
        int position = 50, count = 0;
        for (String line : input.lines()) {
            int direction = line.charAt(0) == 'R' ? 1 : -1;
            int steps = Integer.parseInt(line.substring(1));

            count += countZeros(position, direction, steps);
            position = Math.floorMod(position + direction * steps, 100);
        }
        return count;
    }

    private static int countZeros(int start, int dir, int steps) {
        int next = Math.floorMod(-dir * start, 100);
        if (next == 0) next = 100;
        if (steps < next) return 0;
        return 1 + (steps - next) / 100;
    }
}
