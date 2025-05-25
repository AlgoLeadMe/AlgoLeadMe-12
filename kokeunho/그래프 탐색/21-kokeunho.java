import java.util.*;

public class Main {
    static int N, M;
    static int[][] map = new int[501][501];
    static int[][] dist = new int[501][501];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], 0);
            Arrays.fill(dist[i], INF);
        }

        N = sc.nextInt();
        drawMap(sc, N, 1);

        M = sc.nextInt();
        drawMap(sc, M, 2);


        int cost = dijkstra(0, 0);
        System.out.print(cost);
    }
    public static void drawMap (Scanner sc, int count, int level) {
        for (int i = 0; i < count; i++) {
            int X1 = sc.nextInt(), Y1 = sc.nextInt();
            int X2 = sc.nextInt(), Y2 = sc.nextInt();
            int startX = Math.min(X1, X2);
            int startY = Math.min(Y1, Y2);
            int endX = Math.max(X1, X2);
            int endY = Math.max(Y1, Y2);
            for (int x = startX; x <= endX; x++) {
                for (int y = startY; y <= endY; y++) {
                    map[x][y] = level;
                }
            }
        }
    }
    static int dijkstra (int x, int y) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        pq.add(new Node(x, y, 0));
        dist[x][y] = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            int currentX = current.x;
            int currentY = current.y;
            int currentCost = current.cost;

            if (currentX == 500 && currentY == 500) return currentCost;

            if (dist[currentX][currentY] < currentCost) continue;

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + currentX;
                int ny = dy[i] + currentY;
                int newCost = currentCost;
                if (nx >= 0 && nx < 501 && ny >= 0 && ny < 501 && map[nx][ny] != 2) {
                    if (map[nx][ny] == 1) {
                        newCost++;
                    }
                    if (newCost < dist[nx][ny]) {
                        dist[nx][ny] = newCost;
                        pq.add(new Node(nx, ny, newCost));
                    }
                }
            }
        }
        return -1;
    }

    static class Node {
        int x, y, cost;
        Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

}