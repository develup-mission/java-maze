package maze;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public static Direction from(String rawValue) {
        return switch (rawValue) {
            case "u" -> UP;
            case "d" -> DOWN;
            case "l" -> LEFT;
            case "r" ->  RIGHT;
            default -> throw new IllegalArgumentException("잘못된 입력입니다.");
        };
    }
}
