package prohladenn.vko.aoc2024;

import java.util.HashSet;
import java.util.Scanner;

public class Day06 extends AbstractDay<Day06.Input> {

    public record Input(char[][] map, int i0, int j0) {
    }

    public static void main(String[] args) {
        new Day06().run();
    }

    @Override
    protected String getInputFileName() {
        return "day06.txt";
    }

    @Override
    protected Input readInput(Scanner sc) {
        var map = new char[130][];
        int i = 0, i0 = -1, j0 = -1;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            map[i++] = line.toCharArray();
            if (j0 == -1) {
                var pos = line.indexOf('^');
                if (pos != -1) {
                    i0 = i - 1;
                    j0 = pos;
                }
            }
        }

        return new Input(map, i0, j0);
    }

    private enum Direction {
        UP(-1, 0),
        RIGHT(0, 1),
        DOWN(1, 0),
        LEFT(0, -1);

        private final int di, dj;

        Direction(int di, int dj) {
            this.di = di;
            this.dj = dj;
        }

        public int getDi() {
            return di;
        }

        public int getDj() {
            return dj;
        }

        public Direction turnRight() {
            return switch (this) {
                case UP -> RIGHT;
                case RIGHT -> DOWN;
                case DOWN -> LEFT;
                case LEFT -> UP;
            };
        }
    }

    @Override
    protected Object solvePart1(Input input) {
        int i = input.i0(), j = input.j0();
        var dir = Direction.UP;

        var visited = new HashSet<Pair<Integer, Integer>>();
        visited.add(new Pair<>(i, j));
        input.map()[i][j] = '.';

        while (true) {
            int nextI = i + dir.getDi(), nextJ = j + dir.getDj();
            if (nextI < 0 || nextI >= input.map().length || nextJ < 0 || nextJ >= input.map()[0].length) {
                break;
            }
            if (input.map()[nextI][nextJ] == '#') {
                dir = dir.turnRight();
            } else {
                input.map()[i][j] = 'X';
                i = nextI;
                j = nextJ;
                visited.add(new Pair<>(i, j));
            }
        }

        return visited.size();
    }

    @Override
    protected Object solvePart2(Input input) {
        return null;
    }

}
