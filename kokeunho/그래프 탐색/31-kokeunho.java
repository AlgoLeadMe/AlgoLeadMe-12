import java.util.*;

public class Main {
    static int N, M, K;
    static List<List<Node>> graph;
    static class Node implements Comparable<Node> {
        int to;
        long distance;

        public Node (int to, long distance) {
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return (int)(this.distance - o.distance);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            long distance = sc.nextLong();
            graph.get(to).add(new Node(from, distance));
        }
        int[] interviewCities = new int[K];
        for (int i = 0; i < K; i++) {
            interviewCities[i] = sc.nextInt();
        }

        long[] dist = dijkstra(interviewCities);

        int farthestCity = 0;
        long longestDist = -1;
        for (int i = 1; i <= N; i++) {
            if (dist[i] > longestDist) {
                longestDist = dist[i];
                farthestCity = i;
            }
        }
        System.out.print(farthestCity + "\n" + longestDist);
    }
    static long[] dijkstra(int[] interviewCities) {
        long[] dist = new long[N+1];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int city : interviewCities) {
            dist[city] = 0;
            pq.offer(new Node(city, 0));
        }

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int now = current.to;
            long distance = current.distance;

            if (distance > dist[now]) continue;

            for (Node next : graph.get(now)) {
                long newDistance = dist[now] + next.distance;
                if (newDistance < dist[next.to]) {
                    pq.offer(new Node(next.to, newDistance));
                    dist[next.to] = newDistance;
                }
            }
        }
        return dist;
    }
}