package maze;

public enum Cell {
    WALL, TRACK, GOAL, PLAYER;

    @Override
    public String toString() {
        return switch (this) {
            case WALL -> "1";
            case TRACK -> "0";
            case PLAYER -> "x";
            case GOAL -> "e";
        };
    }

    public static Cell from(String cellString) {
        return switch (cellString) {
            case "1" -> WALL;
            case "0" -> TRACK;
            case "x" -> PLAYER;
            case "e" -> GOAL;
            case null, default -> throw new IllegalArgumentException("파싱 할 수 없는 값입니다.");
        };
    }
}
