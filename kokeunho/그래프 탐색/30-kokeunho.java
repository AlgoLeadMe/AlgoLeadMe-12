import java.util.*;

public class Main {
    static int N, M;
    static List<List<Node>> graph;
    static class Node implements Comparable<Node>{
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        int maxWeight = 0;
        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();
            graph.get(from).add(new Node(to, weight));
            graph.get(to).add(new Node(from, weight));
            maxWeight = Math.max(maxWeight, weight);
        }
        int A = sc.nextInt();
        int B = sc.nextInt();

        int left = 1;
        int right = maxWeight;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (bfs(A, B, mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.print(answer);
    }
    static boolean bfs(int start, int end, int weightLimit) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            if (now == end) return true;

            for (Node next : graph.get(now)) {
                if (!visited[next.to] && next.weight >= weightLimit) {
                    queue.add(next.to);
                    visited[next.to] = true;
                }
            }
        }
        return false;
    }
}