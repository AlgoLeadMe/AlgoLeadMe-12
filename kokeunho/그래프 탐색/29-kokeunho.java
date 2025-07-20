import java.util.*;

public class Main {
    static int R, C;
    static int escapeCount = Integer.MAX_VALUE;
    static char[][] map;
    static int[][] fireCount;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        map = new char[R][C];
        List<int[]> firePos = new ArrayList<>();
        int jx = 0, jy = 0;
        for (int i = 0; i < R; i++) {
            String line = sc.next();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'J') {
                    jx = i;
                    jy = j;
                }
                if (map[i][j] == 'F') {
                    firePos.add(new int[]{i, j});
                }
            }
        }
        fireCount = new int[R][C];
        for (int i = 0; i < R; i++) {
            Arrays.fill(fireCount[i], Integer.MAX_VALUE);
        }
        for (int[] pos : firePos) {
            fireBfs(pos[0], pos[1]);
        }
        visited = new boolean[R][C];
        jihoonBfs(jx, jy);

        System.out.println(escapeCount == Integer.MAX_VALUE ? "IMPOSSIBLE" : escapeCount);
    }
    static void fireBfs (int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        fireCount[x][y] = 0;
        queue.add(new int[]{x, y});

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];
                if (nx >= 0 && nx < R && ny >= 0 && ny < C
                        && fireCount[nx][ny] > fireCount[current[0]][current[1]]+1 && map[nx][ny] != '#') {
                    queue.add(new int[]{nx, ny});
                    fireCount[nx][ny] = fireCount[current[0]][current[1]]+1;
                }
            }
        }
    }
    static void jihoonBfs (int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        visited[x][y] = true;
        queue.add(new int[]{x, y, 0});

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];
                if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                    escapeCount = Math.min(escapeCount, current[2] + 1);
                    continue;
                }
                if (!visited[nx][ny] && map[nx][ny] != '#' && current[2] + 1 < fireCount[nx][ny]) {
                    queue.add(new int[]{nx, ny, current[2] + 1});
                    visited[nx][ny] = true;
                }
            }
        }
    }
}