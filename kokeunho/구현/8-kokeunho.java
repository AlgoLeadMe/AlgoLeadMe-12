package org.example;

import java.sql.SQLOutput;
import java.util.*;

public class Main {

    static int N, R, L;
    static int[][] map;
    static boolean[][] visited;
    static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();

        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int day_count = 0;

        while(true) {
            visited = new boolean[N][N];
            boolean moved = false;

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(!visited[i][j]) {
                        List<int[]> union = new ArrayList<> ();
                        int populationSum = bfs(i, j, union);
                        if(populationSum > 0) {
                            moved = true;
                            int newPopulation = populationSum / union.size();
                            for(int[] country : union) {
                                map[country[0]][country[1]] = newPopulation;
                            }
                        }
                    }
                }
            }
            if(!moved) {
                break;
            }
            day_count++;
        }

        System.out.println(day_count);
    }

    private static int bfs(int x, int y, List<int[]> union) {
        Queue<int[]> queue = new LinkedList<> ();
        queue.add(new int[]{x, y});
        union.add(new int[]{x, y});
        visited[x][y] = true;
        int populationSum = map[x][y];

        while(!queue.isEmpty()) {
            int[] now_position = queue.poll();
            int nowX = now_position[0];
            int nowY = now_position[1];

            for(int[] dir : direction) {
                int nx = nowX + dir[0];
                int ny = nowY + dir[1];

                if(nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                    int diff = Math.abs(map[nowX][nowY] - map[nx][ny]);
                    if(diff >= L && diff <= R) {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                        union.add(new int[]{nx, ny});
                        populationSum += map[nx][ny];
                    }
                }
            }
        }
        return union.size() > 1 ? populationSum : 0;
    }
}