package maze;

public class Player {

    private int x;
    private int y;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(Direction direction, Cell[][] maze) {
        int nx = getNx(direction);
        int ny = getNy(direction);
        if (nx < 0  || nx >= maze[0].length || ny < 0 || ny >= maze.length || maze[nx][ny] == Cell.WALL) {
            throw new IllegalArgumentException("움직일 수 없는 방향입니다.");
        }
        maze[x][y] = Cell.TRACK;
        x = nx;
        y = ny;
        maze[nx][ny] = Cell.PLAYER;
    }

    private int getNx(Direction direction) {
        if (direction == Direction.DOWN) {
            return x + 1;
        }
        if (direction == Direction.UP) {
            return x - 1;
        }
        return x;
    }

    private int getNy(Direction direction) {
        if (direction == Direction.LEFT) {
            return y - 1;
        }
        if (direction == Direction.RIGHT) {
            return y + 1;
        }
        return y;
    }
}
