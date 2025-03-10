import java.util.*;

public class Main {
    static int N, M, K, X;
    static final int INF = Integer.MAX_VALUE;
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] distance;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        X = sc.nextInt();

        distance = new int[N + 1];
        visited = new boolean[N + 1];
        Arrays.fill(distance, INF);
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            graph.get(start).add(end);
        }

        dijkstra2(X);

        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (distance[i] == K) {
                result.add(i);
            }
        }
        if (result.isEmpty()) {
            System.out.print(-1);
        } else {
            for (int index : result) {
                System.out.print(index);
                System.out.print("\n");
            }
        }
    }

    public static void dijkstra1(int start) {
        distance[start] = 0;

        for (int i = 1; i <= N; i++) {
            int minVertex = -1;
            int minDist = INF;
            for (int j = 1; j <= N; j++) {
                if (!visited[j] && distance[j] < minDist) {
                    minDist = distance[j];
                    minVertex = j;
                }
            }
            if (minVertex == -1) break;
            visited[minVertex] = true;

            for (int next : graph.get(minVertex)) {
                if (distance[minVertex] + 1 < distance[next]) {
                    distance[next] = distance[minVertex] + 1;
                }
            }

        }
    }

    public static void dijkstra2(int start) {
        PriorityQueue<int []> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.add(new int[]{start, 0});
        distance[start] = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentNode = current[0];
            int currentDist = current[1];

            if (currentDist > distance[currentNode]) continue;

            for (int next : graph.get(currentNode)) {
                if (currentDist + 1 < distance[next]) {
                    distance[next] = currentDist + 1;
                    pq.add(new int[]{next, distance[next]});
                }
            }
        }

    }
}