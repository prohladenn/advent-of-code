package prohladenn.vko.aoc2024;

import prohladenn.vko._template.AbstractDay;

import java.util.*;

public class Day08 extends AbstractDay<Day08.Input> {

    protected record Input(
            int width,
            int height,
            Map<Character, List<Pair<Integer, Integer>>> map
    ) {
    }

    public static void main(String[] args) {
        new Day08().run();
    }

    @Override
    protected String getInputFileName() {
        return "day08.txt";
    }

    @Override
    protected Input readInput(Scanner sc) {
        var data = new HashMap<Character, List<Pair<Integer, Integer>>>();

        int i = 0, width = -1;
        while (sc.hasNextLine()) {
            var line = sc.nextLine();
            if (width == -1) width = line.length();
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '.') continue;
                var coordinates = Pair.of(i, j);
                data.computeIfAbsent(line.charAt(j), k -> new ArrayList<>()).add(coordinates);
            }
            i++;
        }

        return new Input(width, i, data);
    }

    @Override
    protected Object solvePart1(Input input) {
        var result = new HashSet<Pair<Integer, Integer>>();

        for (var entry : input.map().entrySet()) {
            var list = entry.getValue();
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    var coord1 = list.get(i);
                    var coord2 = list.get(j);

                    var dx = coord2.left() - coord1.left();
                    var dy = coord2.right() - coord1.right();

                    var antinode1 = Pair.of(coord1.left() - dx, coord1.right() - dy);
                    if (isValidAntinode(antinode1, input)) result.add(antinode1);

                    var antinode2 = Pair.of(coord2.left() + dx, coord2.right() + dy);
                    if (isValidAntinode(antinode2, input)) result.add(antinode2);
                }
            }
        }

        return result.size();
    }

    @Override
    protected Object solvePart2(Input input) {
        var result = new HashSet<Pair<Integer, Integer>>();

        for (var entry : input.map().entrySet()) {
            var list = entry.getValue();
            result.addAll(list);
            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    var coord1 = list.get(i);
                    var coord2 = list.get(j);

                    var dx = coord2.left() - coord1.left();
                    var dy = coord2.right() - coord1.right();

                    int multiplier = 1;
                    while (isValidAntinode(Pair.of(coord1.left() - dx * multiplier, coord1.right() - dy * multiplier), input)) {
                        result.add(Pair.of(coord1.left() - dx * multiplier, coord1.right() - dy * multiplier));
                        multiplier++;
                    }

                    multiplier = 1;
                    while (isValidAntinode(Pair.of(coord2.left() + dx * multiplier, coord2.right() + dy * multiplier), input)) {
                        result.add(Pair.of(coord2.left() + dx * multiplier, coord2.right() + dy * multiplier));
                        multiplier++;
                    }
                }
            }
        }

        return result.size();
    }

    private boolean isValidAntinode(Pair<Integer, Integer> antinode, Input input) {
        return antinode.left() >= 0 && antinode.left() < input.height()
                && antinode.right() >= 0 && antinode.right() < input.width();
    }
}