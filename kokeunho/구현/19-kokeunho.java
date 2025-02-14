import java.util.*;

public class Main {
    static int n, m;
    static int mv, result = 0;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
                mv = Math.max(mv, map[i][j]);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                findMaxValue(i, j, 1, map[i][j]);
                visited[i][j] = false;

                checkTShape(i, j);
            }
        }

        System.out.println(result);
    }

    public static void findMaxValue(int x, int y, int count, int sum) {
        if (count == 4) {
            result = Math.max(result, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
                visited[nx][ny] = true;
                findMaxValue(nx, ny, count + 1, sum + map[nx][ny]);
                visited[nx][ny] = false;
            }
        }
    }

    public static void checkTShape(int x, int y) {
        int[][] tShape = {
                {0, 1, 0, -1, 1, 0}, //ㅜ
                {1, 0, 0, -1, -1, 0}, //ㅓ
                {-1, 0, 0, -1, 0, 1}, //ㅗ
                {-1, 0, 1, 0, 0, 1} //ㅏ
        };

        for (int i = 0; i < 4; i++) {
            int sum = map[x][y];
            boolean isValid = true;

            for (int j = 0; j < 3; j++) {
                int nx = x + tShape[i][2 * j];
                int ny = y + tShape[i][2 * j + 1];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    isValid = false;
                    break;
                }
                sum += map[nx][ny];
            }
            if (isValid) {
                result = Math.max(result, sum);
            }
        }
    }
}