package beakjoon;

import java.util.*;
import java.io.*;

public class Sol21609 {

    static final int EMPTY = -2;
    static int n, m;
    static int[][] board;
    static int[][] offset = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        input();
        int total = 0;

        while (true) {
            int[] base = {-1, -1, -1, -1}; // 기준블록
            boolean[][] visited = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] <= 0 || visited[i][j]) continue;
                    int[] result = bfs(j, i, visited); // 순서 : 블록 사이즈 -> 무지개 숫자 -> 행 -> 열

                    if (compare(result, base) > 0) {
                        base = result;
                    }
                }
            }

            if (base[0] == -1) break;

            // delete blocks and get points
            int p = delete_bfs(base[3], base[2]);
            total += p * p;

            pull();
            board = rotate();
            pull();
        }
        System.out.println(total);
    }

    private static int compare(int[] a, int[] b) {
        for (int i = 0; i < 4; i++) {
            if (a[i] != b[i]) return Integer.compare(a[i], b[i]);
        }
        return 0;
    }

    private static int delete_bfs(int cx, int cy) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];
        int color = board[cy][cx];

        q.add(new int[]{cx, cy});
        visited[cy][cx] = true;
        board[cy][cx] = EMPTY;
        int count = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + offset[d][0];
                int ny = y + offset[d][1];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[ny][nx]) continue;
                if (board[ny][nx] == 0 || board[ny][nx] == color) {
                    q.add(new int[]{nx, ny});
                    visited[ny][nx] = true;
                    board[ny][nx] = EMPTY;
                    count++;
                }
            }
        }
        return count;
    }

    private static int[] bfs(int cx, int cy, boolean[][] globalVisited) {
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> q = new ArrayDeque<>();
        List<int[]> blocks = new ArrayList<>();
        List<int[]> rainbows = new ArrayList<>();
        int color = board[cy][cx];

        q.add(new int[]{cx, cy});
        visited[cy][cx] = true;
        globalVisited[cy][cx] = true; // 무지개 블록은 중복 방문 가능하기 때문에 visited 배열을 두 개 써야함
        blocks.add(new int[]{cx, cy});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + offset[d][0];
                int ny = y + offset[d][1];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[ny][nx]) continue;
                if (board[ny][nx] == 0 || board[ny][nx] == color) {
                    visited[ny][nx] = true;
                    q.add(new int[]{nx, ny});
                    blocks.add(new int[]{nx, ny});

                    if (board[ny][nx] == 0) {
                        rainbows.add(new int[]{nx, ny});
                    } else {
                        globalVisited[ny][nx] = true;
                    }
                }
            }
        }
        
        // 무지개 블록은 다른 그룹에서도 사용될 수 있도록 방문 표시 해제
        for (int[] b : rainbows) {
            globalVisited[b[1]][b[0]] = false;
        }

        // 블록 그룹 크기가 2 미만 제외
        if (blocks.size() < 2) {
            return new int[]{-1, -1, -1, -1};
        }

        // 기준 블록 찾기 (무지개 블록이 아닌 블록 중에서)
        int[] base = null;
        for (int[] block : blocks) {
            if (board[block[1]][block[0]] != 0) {
                if (base == null) {
                    base = block;
                } else {
                    // 행 번호가 작은 것을 우선, 같다면 열 번호가 작은 것을 우선
                    if (block[1] < base[1] || (block[1] == base[1] && block[0] < base[0])) {
                        base = block;
                    }
                }
            }
        }
        
        // 기준 블록이 없으면 무효
        if (base == null) {
            return new int[]{-1, -1, -1, -1};
        }

        // 순서 : 블록 사이즈 -> 무지개 숫자 -> 행 -> 열
        return new int[]{blocks.size(), rainbows.size(), base[1], base[0]};
    }

    private static int[][] rotate() {
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = board[j][n - i - 1];
            }
        }

        return result;
    }

    private static void pull() {
        for (int j = 0; j < n; j++) {
            for (int i = n - 2; i >= 0; i--) {
                if (board[i][j] < 0) continue;

                int ny = i;
                while (ny + 1 < n && board[ny + 1][j] == EMPTY) {
                    if (board[ny][j] == -1) break;  // 검은 블록은 이동하지 않음
                    board[ny + 1][j] = board[ny][j];
                    board[ny][j] = EMPTY;
                    ny++;
                }
            }
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();
    }
}
