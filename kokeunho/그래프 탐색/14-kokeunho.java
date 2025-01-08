package org.example;

import java.util.*;

public class Main {

    static int n;
    static int[][] map;
    static int[][] distance;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        map = new int[n][n];
        distance = new int[n][n];

        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j) - '0';
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        System.out.println(bfs(0,0));
    }
    public static int bfs(int sx, int sy) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[]{0, 0, 0});
        distance[sx][sy] = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int x = current[0];
            int y = current[1];
            int brokenwall = current[2];

            if (x == n - 1  && y == n - 1) return brokenwall;

            if (distance[x][y] < brokenwall) continue;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    int newBrokenwall = brokenwall + (map[nx][ny] == 0 ? 1 : 0);

                    if (newBrokenwall < distance[nx][ny]) {
                        distance[nx][ny] = newBrokenwall;
                        pq.offer(new int[]{nx, ny, newBrokenwall});
                    }
                }
            }
        }
        return -1;
    }
}