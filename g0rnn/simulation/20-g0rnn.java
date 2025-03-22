package beakjoon;

import java.util.*;
import java.io.*;

public class Sol17144 {

    static int R, C, T;
    static int botY1, botY2;
    static int[][] offset = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}}; // 상우하좌
    static int[][] grid = new int[51][51];
    static int[][] spread;

    public static void main(String[] args) throws IOException {
        input();
        while (true) {
            spread();
            work();
            work2();
            T -= 1;
            if (T == 0) break;
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(count()));
        bw.flush();
        bw.close();
    }

    public static int count() {
        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sum += grid[i][j];
            }
        }
        return sum + 2;
    }

    // 반시계
    public static void work() {
        // right
        int pushedRight = grid[botY1][C - 1];
        for (int i = C - 1; i > 1; i--) {
            grid[botY1][i] = grid[botY1][i - 1];
        }

        // up
        int pushedEdge = grid[0][C - 1];
        for (int i = 0; i < botY1 - 1; i++) {
            grid[i][C - 1] = grid[i + 1][C - 1];
        }

        // left
        int pushedLeft = grid[0][0];
        for (int i = 0; i < C - 1; i++) {
            grid[0][i] = grid[0][i + 1];
        }

        // down or up
        for (int i = botY1 - 1; i > 0; i--) {
            grid[i][0] = grid[i - 1][0];
        }

        grid[botY1][1] = 0; // fresh air
        grid[botY1 - 1][C - 1] = pushedRight;
        grid[0][C - 2] = pushedEdge;
        grid[1][0] = pushedLeft;
    }

    // 시계
    public static void work2() {
        // right
        int pushedRight = grid[botY2][C - 1];
        for (int i = C - 1; i > 1; i--) {
            grid[botY2][i] = grid[botY2][i - 1];
        }

        // up or down
        int pushedEdge = grid[R - 1][C - 1];
        for (int i = R - 1; i > botY2 + 1; i--) {
            grid[i][C - 1] = grid[i - 1][C - 1];
        }

        // left
        int pushedLeft = grid[R - 1][0];
        for (int i = 0; i < C - 2; i++) {
            grid[R - 1][i] = grid[R - 1][i + 1];
        }

        // up
        for (int i = botY2 + 1; i < R - 2; i++) {
            grid[i][0] = grid[i + 1][0];
        }

        grid[botY2][1] = 0; // fresh air
        grid[botY2 + 1][C - 1] = pushedRight;
        grid[R - 1][C - 2] = pushedEdge;
        grid[R - 2][0] = pushedLeft;
    }

    public static void spread() {
        spread = new int[51][51];
        for (int i = 0;  i < R; i++) {
            for (int j = 0;  j < C; j++) {
                if (grid[i][j] <= 0) continue;

                int amount = grid[i][j] / 5;
                for (int dir = 0; dir < 4; dir++) {
                    int nx = j + offset[dir][0];
                    int ny = i + offset[dir][1];
                    if (!canMove(nx, ny)) continue;
                    // move to each dir
                    spread[ny][nx] += amount;
                    grid[i][j] -= amount;
                }

            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                grid[i][j] += spread[i][j];
            }
        }
    }

    public static boolean canMove(int x, int y) {
        return 0 <= x && x < C && 0 <= y && y < R && grid[y][x] != -1;
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i< R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == -1) {
                    if (botY1 == 0) {
                        botY1 = i;
                    } else {
                        botY2 = i;
                    }
                }
            }
        }
        br.close();
    }
}
