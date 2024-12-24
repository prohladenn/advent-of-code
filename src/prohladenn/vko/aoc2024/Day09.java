package prohladenn.vko.aoc2024;

import prohladenn.vko._template.AbstractDay;
import prohladenn.vko._template.Inputs.InputLine;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Day09 extends AbstractDay<InputLine> {

    public static void main(String[] args) {
        new Day09().run();
    }

    @Override
    protected String getInputFileName() {
        return "day09.txt";
    }

    @Override
    protected InputLine readInput(Scanner sc) {
        return InputLine.fromSc(sc);
    }

    @Override
    protected Object solvePart1(InputLine input) {
        var data = transform(input.line());

        int l = 0, r = data.size() - 1;
        while (l < r) {
            while (l < r && data.get(l) != -1) l++;
            while (l < r && data.get(r) == -1) r--;
            if (l < r && data.get(l) == -1 && data.get(r) != -1) {
                data.set(l++, data.get(r));
                data.set(r--, -1);
            }
        }

        return sum(data);
    }

    @Override
    protected Object solvePart2(InputLine input) {
        var data = transform(input.line());

        int l, r = data.size() - 1;
        while (r > 0) {
            while (r > 0 && data.get(r) == -1) r--;
            if (r <= 0) break;
            l = r;

            while (l > 0 && Objects.equals(data.get(l), data.get(l - 1))) l--;
            var len = r - l + 1;

            for (int start = 0; start <= l - len; start++) {
                if (start + len - 1 < data.size() && isBlank(data, start, start + len - 1)) {
                    copy(data, l, r, start);
                    break;
                }
            }

            r = l - 1;
        }

        return sum(data);
    }

    private List<Integer> transform(String line) {
        var data = new ArrayList<Integer>(line.length() * 3);

        int n = 0;
        boolean isFile = true;
        for (char c : line.toCharArray()) {
            for (int i = 0; i < c - '0'; i++) data.add(isFile ? n : -1);
            if (isFile) n++;
            isFile = !isFile;
        }

        return data;
    }

    private long sum(List<Integer> data) {
        return IntStream.range(0, data.size())
                .mapToLong(i -> Math.max(data.get(i) * i, 0))
                .sum();
    }

    private boolean isBlank(List<Integer> data, int l, int r) {
        return data.subList(l, r + 1).stream().allMatch(i -> i == -1);
    }

    private void copy(List<Integer> data, int l, int r, int start) {
        for (int i = l; i <= r; i++) {
            data.set(start++, data.get(i));
            data.set(i, -1);
        }
    }
}
