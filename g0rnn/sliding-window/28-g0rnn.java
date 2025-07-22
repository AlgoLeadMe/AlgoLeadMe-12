package beakjoon;

import java.util.*;
import java.io.*;

public class Sol23295 {

    static int[] schedules = new int[100_000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            int m = Integer.parseInt(br.readLine());
            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                for (int k = start; k < end; k++) {
                    schedules[k] += 1;
                }
            }
        }
        br.close();

        int window = 0;
        for (int i = 0; i < t; i++) window += schedules[i];

        int maxTime = window;
        int start = 0;

        for (int i = 1; i <= 100_000 - t; i++) {
            window = window - schedules[i - 1] + schedules[i + t - 1];
            if (window > maxTime) {
                maxTime = window;
                start = i;
            }
        }

        System.out.println(start + " " + (start + t));
    }
}
