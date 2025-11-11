import java.util.*;

class Solution {
    static int col, row;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] sumAreaPerCol;
    static boolean[][] visited;

    public int solution(int[][] land) {
        col = land[0].length;
        row = land.length;
        sumAreaPerCol = new int[col];
        visited = new boolean[row][col];

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (land[j][i] == 1 && !visited[j][i]) {
                    bfs(j, i, land);
                }
            }
        }

        int maxArea = 0;
        for (int i = 0; i < col; i++) {
            maxArea = Math.max(maxArea, sumAreaPerCol[i]);
        }
        return maxArea;
    }
    public void bfs(int x, int y, int[][] land) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;
        int area = 1;
        Set<Integer> colList = new HashSet<>();
        colList.add(y);

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];
                if (nx >= 0 && nx < row && ny >= 0 && ny < col && !visited[nx][ny] && land[nx][ny] == 1) {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    area++;
                    colList.add(ny);
                }
            }
        }

        for (int column : colList) {
            sumAreaPerCol[column] += area;
        }
    }
}