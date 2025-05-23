import java.util.*;

class Solution {

    public boolean[] findAnswer(int n, int[][] edges) {
        boolean[] ans = new boolean[edges.length];
        List<List<int[]>> graph = initGraph(n, edges);

        int[] dist = dijkstra(graph, 0);
        int[] dist2 = dijkstra(graph, n-1);

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];

            ans[i] = dist[u] + w + dist2[v] == dist[n-1] || dist[v] + w + dist2[u] == dist[n-1];
        }

        return ans;
    }

    private List<List<int[]>> initGraph(int n, int[][] edges) {
        List<List<int[]>> graph = new ArrayList<>();

        // 각 노드마다 리스트 초기화
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];
            int weight = edge[2];

            graph.get(start).add(new int[]{end, weight});
            graph.get(end).add(new int[]{start, weight}); // 양방향 그래프
        }

        return graph;
    }

    private int[] dijkstra(List<List<int[]>> graph, int src) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])); // minHeap, int[] = {dist, nodeNum}
        int[] distance = new int[graph.size()]; // distance[i] = 0 ~ i번째 노드까지 거리
        
        Arrays.fill(distance, 1_000_000_000);
        distance[src] = 0; // 시작 주소는 거리가 0
        pq.add(new int[]{0, src});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int nodeDist = cur[0];
            int node = cur[1];

            if (nodeDist > distance[node]) continue;

            // 인접 노드 확인 후 갱신
            for (int[] neighbor : graph.get(node)) {
                int nextNode = neighbor[0];
                int weight = neighbor[1];

                if (distance[node] + weight < distance[nextNode]) {
                    distance[nextNode] = distance[node] + weight;
                    pq.add(new int[]{distance[nextNode], nextNode});
                }
            }
        }
        return distance;
    }
}