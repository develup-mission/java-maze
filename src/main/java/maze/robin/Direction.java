package maze.robin;

public enum Direction {

    UP(0,-1),
    DOWN(0,1),
    LEFT(-1,0),
    RIGHT(1,0),
    ;

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static Direction from(String string) {
        return switch (string) {
            case "l" -> LEFT;
            case "r" -> RIGHT;
            case "u" -> UP;
            case "d" -> DOWN;
            default -> throw new IllegalArgumentException("잘못된 방향 입력입니다.");
        };
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
