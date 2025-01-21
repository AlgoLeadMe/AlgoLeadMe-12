package org.example;

import java.util.*;

public class Main {

    static int N, M, K;
    static int x, y;
    static int[][] path;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        path = new int[N][M];

        int mx = 0, my = 0;
        if (K != 0) {
            mx = (K-1) / M;
            my = (K-1) % M;
        }
        if (K == 0) {
            System.out.println(findPath(0,0, N-1, M-1));
        } else {
            int a = findPath(0,0, mx, my);
            int b = findPath(mx, my, N-1, M-1);
            System.out.println(a*b);
        }
    }
    public static int findPath (int sx, int sy, int ex, int ey) {
        for (int i = 0; i <= ex; i++) {
            for (int j = 0; j <= ey; j++) {
                path[i][j] = 0;
            }
        }

        path[sx][sy] = 1;
        for (int i = sx; i <= ex; i++) {
            for (int j = sy; j <= ey; j++) {
                if (i > sx) path[i][j] += path[i-1][j];
                if (j > sy) path[i][j] += path[i][j-1];
            }
        }
        return path[ex][ey];
    }
}