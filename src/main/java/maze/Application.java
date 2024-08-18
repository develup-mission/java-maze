package maze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import maze.robin.Maze;
import maze.robin.RandomNumberGenerator;

public class Application {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("미로의 가로와 세로 크기를 입력하세요.(예시 : 8,8)");

        String readLine = br.readLine();
        int n = Integer.parseInt(readLine.split(",")[0]);
        int m = Integer.parseInt(readLine.split(",")[1]);

        System.out.println("미로를 입력해 주세요. 0은 통로 4는 벽입니다.");
        List<String> mazeString = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            mazeString.add(br.readLine());
        }
        Maze maze = new Maze(mazeString, new RandomNumberGenerator());

        System.out.println("맵에 아이템과 플레이어와 미노타우로스가 스폰됩니다.");

        System.out.println(maze.getCellString());
        System.out.println();

        while (true) {
            System.out.print("플레이어 이동 : ");
            String direction = br.readLine();
            maze.movePlayer(direction);
            System.out.println(maze.getCellString());
            System.out.println();

            System.out.print("미노타우로스 이동 : ");
            direction = br.readLine();
            maze.moveMonster(direction);
            System.out.println(maze.getCellString());
            System.out.println();

            System.out.print("미노타우로스 이동 : ");
            direction = br.readLine();
            maze.moveMonster(direction);
            System.out.println(maze.getCellString());
            System.out.println();
        }
    }
}
