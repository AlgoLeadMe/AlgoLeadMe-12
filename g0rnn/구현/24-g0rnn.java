package beakjoon;

import java.io.*;
import java.util.*;

public class Sol1016 {

    static long min, max;

    public static void main(String[] args) throws IOException {
        input();
        long ans = max - min + 1; // 살펴볼 숫자 개수
        boolean[] visited = new boolean[(int)(max - min + 1)];
        long i = 2;

        while (i * i <= max) {
            long pow = i * i; // 제곱수
            long start = min / pow; // min보다 작은 제곱수의 배수를 개수
            if (min % pow != 0) {
                start += 1;
            }

            // 제곱 수의 배수에 방문 표시
            for (long j = start; j * pow <= max; j++) {
                int idx = (int)(j * pow - min);
                if (!visited[idx]) {
                    visited[idx] = true;
                    ans--; // 방문했으면 정답에서 제외
                }
            }
            i++;
        }
        System.out.println(ans);
    }


    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        min = Long.parseLong(st.nextToken());
        max = Long.parseLong(st.nextToken());
        br.close();
    }
}
