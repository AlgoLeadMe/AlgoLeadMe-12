package beakjoon;

import java.util.*;
import java.io.*;

class Sol2805 {
    static long N, M;
    static int maxHeight;
    static int[] branch;

    public static void main(String[] args) throws IOException {
        input();
        int start = 0;
        int end = maxHeight;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (check(mid)) start = mid + 1;
            else end = mid - 1;
        }
        output(end);
    }

    // 목표치보다 큰지
    public static boolean check(int height) {
        long sum = 0;
        for (int i = 0; i < branch.length; i++) {
            if (branch[i] > height) {
                sum += branch[i] - height;
            }
        }
        return sum >= M;
    }

    public static void output(int answer) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.flush();
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken()); M = Long.parseLong(st.nextToken());
        branch = new int[(int) N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            branch[i] = Integer.parseInt(st.nextToken());
            if (branch[i] > maxHeight) maxHeight = branch[i];
        }
    }
}
