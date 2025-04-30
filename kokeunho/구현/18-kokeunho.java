import java.util.*;

public class Main {
    static int n, m;
    static int r, c, d;
    static int[][] map;
    static boolean[][] cleaned;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        r = sc.nextInt();
        c = sc.nextInt();
        d = sc.nextInt();

        map = new int[n][m];
        cleaned = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int result = start(r, c);
        System.out.println(result);
    }
    public static void turnLeft() {
        d = (d + 3) % 4;
    }
    public static int start(int startX, int startY) {
        int count = 0;

       while (true) {
           if (!cleaned[startX][startY]) {
               cleaned[startX][startY] = true;
               count++;
           }

           boolean moved = false;

           for (int i = 0; i < 4; i++) {
               turnLeft();
               int nx = startX + dx[d];
               int ny = startY + dy[d];

               if (nx >= 0 && nx < n && ny >= 0 && ny < m
                       && map[nx][ny] == 0 && !cleaned[nx][ny]) {
                   startX = nx;
                   startY = ny;
                   moved = true;
                   break;
               }
           }

           if (!moved) {
               int bx = startX - dx[d];
               int by = startY - dy[d];

               if (bx >= 0 && bx < n && by >= 0 && by <m && map[bx][by] == 0) {
                   startX = bx;
                   startY = by;
               } else {
                   break;
               }
           }
       }
       return count;
    }

}