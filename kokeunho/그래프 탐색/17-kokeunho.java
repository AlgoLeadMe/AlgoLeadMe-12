import java.util.*;

public class Main {
    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        map = new int[n][n];
        visited = new boolean[n][n];

        int max_height = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                max_height = Math.max(max_height, map[i][j]);
            }
        }

        int max_area = 0;
        for (int h = 0; h <= max_height; h++) {
            visited = new boolean[n][n];
            int safe_area = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j] && map[i][j] > h) {
                        dfs(i, j, h);
                        safe_area++;
                    }
                }
            }
            max_area = Math.max(max_area, safe_area);
        }
        System.out.print(max_area);
    }
    public static void dfs(int startX, int startY, int height) {
        visited[startX][startY] = true;
        for (int i = 0; i < 4; i++) {
            int nx = startX + dx[i];
            int ny = startY + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny] && map[nx][ny] > height) {
                dfs(nx, ny, height);
            }
        }
    }
}