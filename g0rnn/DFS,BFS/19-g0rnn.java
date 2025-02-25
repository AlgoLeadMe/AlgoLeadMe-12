package beakjoon;

import java.util.*;
import java.io.*;

public class Sol13549 {

    static int N, M;
    static int[] time = new int[100001];

    public static void main(String[] args) throws Exception {
        input();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(bfs()));
        bw.flush();
        bw.close();
    }

    public static int bfs() {
        Deque<Integer> dq = new ArrayDeque<>();
        Arrays.fill(time, Integer.MAX_VALUE);
        time[N] = 0;
        dq.add(N);

        while (!dq.isEmpty()) {
            int cur = dq.pollFirst();

            if (cur * 2 <= 100000 && time[cur * 2] > time[cur]) {
                time[cur * 2] = time[cur];
                dq.add(cur * 2);
            }
            if(cur + 1 <= 100000 && time[cur + 1] > time[cur] + 1) {
                time[cur + 1] = time[cur] + 1;
                dq.add(cur + 1);
            }
            if (cur - 1 >= 0 && time[cur - 1] > time[cur] + 1) {
                time[cur - 1] = time[cur] + 1;
                dq.add(cur - 1);
            }
        }
        return time[M];
    }

    public static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        br.close();
    }
}
