package maze;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Maze {

    private final Player player;
    private final Cell[][] maze;

    public static Maze from(List<String> mazeString) {
        int x = mazeString.get(0).split(" ").length;
        int y = mazeString.size();
        Cell[][] cells = new Cell[x][y];
        for (int i = 0; i < x; i++) {
            String string = mazeString.get(i);
            String[] rawCells = string.split(" ");
            for (int j = 0; j < y; j++) {
                cells[i][j] = Cell.from(rawCells[j]);
            }
        }
        return new Maze(cells);
    }

    public Maze(Cell[][] maze) {
        this.player = new Player(0, 1);
        this.maze = maze;
        maze[0][1] = Cell.PLAYER;
    }

    public void move(Direction direction) {
        player.move(direction, maze);
    }

    @Override
    public String toString() {
       return Arrays.stream(maze).
                map(cells -> Arrays.stream(cells)
                        .map(Cell::toString).collect(Collectors.joining(" "))
                )
                .collect(Collectors.joining("\n"));
    }

    public boolean isOut() {
        return maze[maze[0].length - 1][maze.length - 2] == Cell.PLAYER;
    }
}
