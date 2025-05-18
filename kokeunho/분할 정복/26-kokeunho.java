import java.util.Scanner;

public class Main {
    static int N;
    static char[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new char[N][N];

        drawPattern(0, 0, N, false);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
    static void drawPattern(int x, int y, int len, boolean blank) {
        if (blank) {
            for (int i = x; i < x + len; i++) {
                for (int j = y; j < y + len; j++) {
                    map[i][j] = ' ';
                }
            }
            return;
        }
        if (len == 1) {
            map[x][y] = '*';
            return;
        }
        int partition = len / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boolean isBlank = false;
                if (i == 1 && j == 1) isBlank = true;
                drawPattern(x+i* partition, y+j*partition, partition, isBlank);
            }
        }
    }
}
