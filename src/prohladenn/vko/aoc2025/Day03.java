package prohladenn.vko.aoc2025;

import prohladenn.vko._template.AbstractDay;
import prohladenn.vko._template.Inputs.InputLines;

import java.util.Scanner;

public class Day03 extends AbstractDay<InputLines> {

    static void main() {
        new Day03().run();
    }

    @Override
    protected String getInputFileName() {
        return "day03.txt";
    }

    @Override
    protected InputLines readInput(Scanner sc) {
        return InputLines.fromSc(sc);
    }

    @Override
    protected Object solvePart1(InputLines input) {
        int sum = 0;

        for (var line : input.lines()) {
            var chars = line.toCharArray();

            int max1 = indexOfLeftMax(chars, 0, chars.length - 1);
            int max2 = indexOfLeftMax(chars, max1 + 1, chars.length);

            sum += (chars[max1] - '0') * 10 + chars[max2] - '0';
        }

        return sum;
    }

    @Override
    protected Object solvePart2(InputLines input) {
        long sum = 0;

        for (var line : input.lines()) {
            var chars = line.toCharArray();

            int max1 = indexOfLeftMax(chars, 0, chars.length - 11);
            int max2 = indexOfLeftMax(chars, max1 + 1, chars.length - 10);
            int max3 = indexOfLeftMax(chars, max2 + 1, chars.length - 9);
            int max4 = indexOfLeftMax(chars, max3 + 1, chars.length - 8);
            int max5 = indexOfLeftMax(chars, max4 + 1, chars.length - 7);
            int max6 = indexOfLeftMax(chars, max5 + 1, chars.length - 6);
            int max7 = indexOfLeftMax(chars, max6 + 1, chars.length - 5);
            int max8 = indexOfLeftMax(chars, max7 + 1, chars.length - 4);
            int max9 = indexOfLeftMax(chars, max8 + 1, chars.length - 3);
            int max10 = indexOfLeftMax(chars, max9 + 1, chars.length - 2);
            int max11 = indexOfLeftMax(chars, max10 + 1, chars.length - 1);
            int max12 = indexOfLeftMax(chars, max11 + 1, chars.length);

            sum += (chars[max1] - '0') * 100_000_000_000L
                    + (chars[max2] - '0') * 10_000_000_000L
                    + (chars[max3] - '0') * 1_000_000_000L
                    + (chars[max4] - '0') * 100_000_000L
                    + (chars[max5] - '0') * 10_000_000L
                    + (chars[max6] - '0') * 1_000_000L
                    + (chars[max7] - '0') * 100_000L
                    + (chars[max8] - '0') * 10_000L
                    + (chars[max9] - '0') * 1_000L
                    + (chars[max10] - '0') * 100L
                    + (chars[max11] - '0') * 10L
                    + (chars[max12] - '0');
        }

        return sum;
    }

    private int indexOfLeftMax(char[] chars, int start, int end) {
        int maxIdx = start;
        for (int i = start + 1; i < end; i++) {
            if (chars[i] > chars[maxIdx]) maxIdx = i;
        }
        return maxIdx;
    }
}
