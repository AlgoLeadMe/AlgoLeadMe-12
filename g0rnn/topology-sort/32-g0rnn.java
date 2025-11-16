package beakjoon;

import java.util.*;
import java.io.*;

public class Sol1766 {
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] indegree = new int[n + 1];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            indegree[v] += 1;
            graph.get(u).add(v);
        }
        br.close();

        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) q.offer(i);
        }

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            Integer cur = q.poll();
            sb.append(cur);
            sb.append(" ");

            for (int next : graph.get(cur)) {
                indegree[next] -= 1;
                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
        System.out.println(sb);
    }

}
