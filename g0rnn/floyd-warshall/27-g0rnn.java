package beakjoon;

import java.util.*;
import java.io.*;

public class Sol1389 {

    private static final int MAX_INT = 1_000_000_000;
    private static int n, m, answer, minKebin = MAX_INT;
    private static Set<Integer>[] relationship;
    private static int[] kebin;
    private static int[][] rel;

    public static void main(String[] args) throws IOException {
        input();
//        kebin = new int[n + 1];
//
//        for (int i = 1; i <= n; i++) {
//            int sum = 0;
//            for (int j = 1; j <= n; j++) {
//                sum += trace(i, j, 0);
//            }
//            kebin[i] = sum;
//        }
//
//        for (int i = 1; i <= n; i++) {
//            if (minKebin > kebin[i]) {
//                minKebin = kebin[i];
//                answer = i;
//            }
//        }

        // 플로이드 와샬
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    rel[i][j] = Math.min(rel[i][j], rel[i][k] + rel[k][j]);
                }
            }
        }

        int tmp = MAX_INT;
        int floydAnswer = 0;

        for (int i = 1; i <= n; i++) {
            int total = 0;

            for (int j = 1; j <= n;j++) {
                total += rel[i][j];
            }

            if (tmp > total) {
                tmp = total;
                floydAnswer = i;
            }
        }

        System.out.println(floydAnswer);
    }

    private static int trace(int start, int dest, int dist) {
        boolean[] visited = new boolean[n + 1];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{start, 0});
        visited[start] = true;

        while (!q.isEmpty()) {
            int cur = q.peek()[0];
            int curDist = q.poll()[1];

            if (cur == dest) return curDist;

            for (Integer friend : relationship[cur]) {
                if (visited[friend]) continue;

                q.offer(new int[]{friend, curDist + 1});
                visited[friend] = true;
            }
        }
        return 0;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 플로이드 와샬
        rel = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                rel[i][j] = MAX_INT;
                if (i == j) rel[i][j] = 0;
            }
        }

        relationship = new HashSet[n + 1];
        for (int i = 1; i <= n; i++) {
            relationship[i] = new HashSet<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            relationship[a].add(b);
            relationship[b].add(a);

            // 플로이드 와샬
            rel[a][b] = 1;
            rel[b][a] = 1;
        }
        br.close();
    }
}
