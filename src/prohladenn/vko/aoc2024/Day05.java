package prohladenn.vko.aoc2024;

import java.util.*;

public class Day05 extends AbstractDay<Day05.Input> {

    protected record Input(
            List<List<Integer>> updatesList,
            List<Pair<Integer, Integer>> rulesList
    ) {
    }

    public static void main(String[] args) {
        new Day05().run();
    }

    @Override
    protected String getInputFileName() {
        return "day05.txt";
    }

    @Override
    protected Input readInput(Scanner sc) {
        List<Pair<Integer, Integer>> rulesList = new ArrayList<>();
        List<List<Integer>> updatesList = new ArrayList<>();

        while (sc.hasNextLine()) {
            var s = sc.nextLine().trim();
            if (s.contains("|")) {
                var rule = s.split("\\|");
                rulesList.add(new Pair<>(Integer.parseInt(rule[0]), Integer.parseInt(rule[1])));
            } else if (!s.isEmpty()) {
                updatesList.add(Arrays.stream(s.split(",")).map(Integer::parseInt).toList());
            }
        }

        return new Input(updatesList, rulesList);
    }

    @Override
    protected Object solvePart1(Input input) {
        return input.updatesList().stream()
                .filter(update -> isCorrectOrder(input.rulesList(), update))
                .mapToInt(Day05::getMiddleElement)
                .sum();
    }

    @Override
    protected Object solvePart2(Input input) {
        return input.updatesList().stream()
                .filter(update -> !isCorrectOrder(input.rulesList(), update))
                .map(update -> reorder(input.rulesList(), update))
                .mapToInt(Day05::getMiddleElement)
                .sum();
    }

    private static int getMiddleElement(List<Integer> update) {
        return update.get((update.size() - 1) / 2);
    }

    private static boolean isCorrectOrder(List<Pair<Integer, Integer>> rules, List<Integer> update) {
        return rules.stream().allMatch(rule -> {
            int leftIndex = update.indexOf(rule.left());
            int rightIndex = update.indexOf(rule.right());
            return leftIndex == -1 || rightIndex == -1 || leftIndex < rightIndex;
        });
    }

    private static List<Integer> reorder(List<Pair<Integer, Integer>> rules, List<Integer> update) {
        List<Integer> sortedUpdate = new ArrayList<>(update);
        while (true) {
            boolean changed = false;
            for (var rule : rules) {
                int leftIndex = sortedUpdate.indexOf(rule.left());
                int rightIndex = sortedUpdate.indexOf(rule.right());
                if (leftIndex != -1 && rightIndex != -1 && leftIndex > rightIndex) {
                    Collections.swap(sortedUpdate, leftIndex, rightIndex);
                    changed = true;
                }
            }
            if (!changed) break;
        }
        return sortedUpdate;
    }
}
