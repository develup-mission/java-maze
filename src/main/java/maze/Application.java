package maze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Application {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("미로의 가로와 세로 크기를 입력하세요.");
        String string = readLine(br);
        String[] split = string.split(",");
        int y = Integer.parseInt(split[0]);
        int x = Integer.parseInt(split[1]);

        System.out.println("미로를 입력합니다.");
        System.out.println("1은 벽 0은 통로 x는 현재 위치 e는 도착점입니다.");

        List<String> inputs = IntStream.range(0, y)
                .mapToObj(value -> readLine(br))
                .toList();

        Maze maze = Maze.from(inputs);
        while (!maze.isOut()) {
            System.out.println("어느 방향으로 이동하겠습니까?");
            Direction direction = getDirectionFromInput(br);
            move(maze, direction);
            System.out.println(maze);
            System.out.println();
        }
        System.out.println("미로 탈출에 성공했습니다! 프로그램을 종료합니다");
    }

    private static String readLine(BufferedReader br) {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Direction getDirectionFromInput(BufferedReader br) {
        String rawDirection = readLine(br);
        return Direction.from(rawDirection);
    }

    private static void move(Maze maze, Direction direction) {
        try {
            maze.move(direction);
        }catch (IllegalArgumentException e) {
            System.out.println("이동할 수 없습니다. 다시 입력하세요");
        }
    }
}
