package maze.robin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class Maze {

    private final List<Cell> cells;

    private Player player;
    private Monster monster;

    public Maze(List<String> cellStrings, NumberGenerator numberGenerator) {
        this.cells = makeCells(cellStrings);
        Set<Integer> specialCellIndexes = numberGenerator.generate(5, getMoveAbleCells().size());
        Queue<Integer> queue = new LinkedList<>(specialCellIndexes);
        initPlayer(queue);
        initMonster(queue);
        initItem(queue);
    }

    private static List<Cell> makeCells(List<String> cellStrings) {
        List<Cell> cells = new ArrayList<>();
        for (int y = 0; y < cellStrings.size(); y++) {
            String cellString = cellStrings.get(y);
            String[] splitCellStrings = cellString.split(" ");
            for (int x = 0; x < splitCellStrings.length; x++) {
                cells.add(new Cell(x, y, Integer.parseInt(splitCellStrings[x])));
            }
        }
        return cells;
    }

    private void initPlayer(Queue<Integer> queue) {
        int playerCellIndex = queue.poll();
        Cell playerCell = getMoveAbleCells().get(playerCellIndex);
        this.player = new Player(playerCell);
    }

    private void initMonster(Queue<Integer> queue) {
        int monsterCellIndex = queue.poll();
        Cell monsterCell = getMoveAbleCells().get(monsterCellIndex);
        this.monster = new Monster(monsterCell);
    }

    private void initItem(Queue<Integer> queue) {
        getMoveAbleCells().get(queue.poll()).value = 1;
        getMoveAbleCells().get(queue.poll()).value = 2;
        getMoveAbleCells().get(queue.poll()).value = 3;
    }

    private List<Cell> getMoveAbleCells() {
        return cells.stream().filter(Cell::isMoveAble).toList();
    }

    public void movePlayer(String directionString) {
        Direction direction = Direction.from(directionString);
        Cell nextCell = findNextCell(player.getNowCell(), direction);
        player.move(nextCell);
    }

    public void moveMonster(String directionString) {
        Direction direction = Direction.from(directionString);
        Cell nextCell = findNextCell(monster.getNowCell(), direction);
        monster.move(nextCell);
    }

    private Cell findNextCell(Cell nowCell, Direction direction) {
        Cell nextCellEquals = new Cell(nowCell.x + direction.getDx(), nowCell.y + direction.getDy(), 0);
        return cells.stream().filter(cell -> cell.equals(nextCellEquals))
                .findFirst()
                .orElseThrow();
    }

    public String getCellString() {
        Map<Integer, List<Cell>> map = cells.stream().collect(Collectors.groupingBy(cell -> cell.y));

        List<Integer> ys = map.keySet().stream().sorted().toList();

        return ys.stream()
                .map(
                        y -> map.get(y).stream().map(cell -> {
                            if (player.isOn(cell)) {
                                return "P";
                            }
                            if (monster.isOn(cell)) {
                                return "M";
                            }
                            return String.valueOf(cell.value);
                        }
                ).collect(Collectors.joining(" "))
                ).collect(Collectors.joining("\n"));
    }


    public static class Cell {

        private final int x;

        private final int y;

        private int value;

        public Cell(int x, int y, int value) {
            this.value = value;
            this.x = x;
            this.y = y;
        }

        public boolean isAdjacent(Cell other) {
            int otherX = other.x;
            int otherY = other.y;

            if (Math.abs(x - otherX) == 1 && y == otherY) {
                return true;
            }
            return Math.abs(y - otherY) == 1 && x == otherX;
        }

        public boolean isWall() {
            return value == 4;
        }

        public boolean isMoveAble() {
            return !isWall();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Cell cell = (Cell) o;
            return x == cell.x && y == cell.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}
