package maze.robin;

public class Monster {

    private Maze.Cell nowCell;

    public Monster(Maze.Cell monsterCell) {
        this.nowCell = monsterCell;
    }

    public void move(Maze.Cell nextCell) {
        boolean adjacent = nowCell.isAdjacent(nextCell);
        boolean moveAble = nextCell.isMoveAble();

        if (!adjacent || !moveAble) {
            throw new IllegalArgumentException("움직일 수 없는 칸입니다.");
        }

        nowCell = nextCell;
    }

    public Maze.Cell getNowCell() {
        return nowCell;
    }

    public boolean isOn(Maze.Cell cell) {
        return cell.equals(nowCell);
    }
}
