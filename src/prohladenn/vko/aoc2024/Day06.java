package prohladenn.vko.aoc2024;

import java.util.HashSet;
import java.util.Scanner;

public class Day06 extends AbstractDay<Day06.Input> {

    public record Input(char[][] map, int startRow, int startCol, Direction direction) {
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
        int i = 0, startRow = -1, startCol = -1;

        while (sc.hasNextLine()) {
            var line = sc.nextLine();
            map[i++] = line.toCharArray();
            if (startCol == -1) {
                var pos = line.indexOf('^');
                if (pos != -1) {
                    startRow = i - 1;
                    startCol = pos;
                }
            }
        }

        return new Input(map, startRow, startCol, Direction.UP);
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

    private record Point(int row, int col) {
    }

    @Override
    protected Object solvePart1(Input input) {
        var map = input.map();
        int row = input.startRow();
        int col = input.startCol();
        var dir = input.direction();

        var visited = new HashSet<Point>();
        visited.add(new Point(row, col));
        map[row][col] = '.';

        while (true) {
            int nextRow = row + dir.getDi();
            int nextCol = col + dir.getDj();
            if (isOutOfBounds(nextRow, nextCol, map)) break;
            if (map[nextRow][nextCol] == '#') {
                dir = dir.turnRight();
            } else {
                row = nextRow;
                col = nextCol;
                visited.add(new Point(row, col));
            }
        }

        return visited.size();
    }

    @Override
    protected Object solvePart2(Input input) {
        var map = input.map();

        int startRow = input.startRow();
        int startCol = input.startCol();
        var startDir = input.direction();

        int row = input.startRow();
        int col = input.startCol();
        var dir = input.direction();

        var possiblePositions = new HashSet<Point>();

        while (true) {
            int nextRow = row + dir.getDi();
            int nextCol = col + dir.getDj();
            if (isOutOfBounds(nextRow, nextCol, map)) break;
            if (map[nextRow][nextCol] == '#') {
                dir = dir.turnRight();
            } else {
                if (createsCycle(map, startRow, startCol, startDir, new Point(nextRow, nextCol))) {
                    possiblePositions.add(new Point(nextRow, nextCol));
                }
                row = nextRow;
                col = nextCol;
            }
        }

        return possiblePositions.size();
    }

    private boolean createsCycle(char[][] map, int startRow, int startCol, Direction startDir, Point cyclePoint) {
        var visitedStates = new HashSet<Pair<Point, Direction>>();

        int row = startRow;
        int col = startCol;
        var dir = startDir;

        while (true) {
            int nextRow = row + dir.getDi();
            int nextCol = col + dir.getDj();
            if (isOutOfBounds(nextRow, nextCol, map)) return false;
            if ((nextRow == cyclePoint.row() && nextCol == cyclePoint.col()) || map[nextRow][nextCol] == '#') {
                dir = dir.turnRight();
            } else {
                if (!visitedStates.add(Pair.of(new Point(nextRow, nextCol), dir))) return true;
                row = nextRow;
                col = nextCol;
            }
        }
    }

    private boolean isOutOfBounds(int row, int col, char[][] map) {
        return row < 0 || row >= map.length || col < 0 || col >= map[0].length;
    }
}
