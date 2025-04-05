package beakjoon;

import java.io.*;
import java.util.*;

public class Sol12015 {

    static int N;
    static int answer;
    static int[] arr;
    static List<Integer> longest = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        input();

        longest.add(arr[0]);
        for (int i = 1; i < N; i++) {
            if (arr[i] > longest.get(longest.size() - 1)) {
                longest.add(arr[i]);
            } else {
                int idx = bSearchIdx(arr[i]);
                longest.set(idx, arr[i]);
            }
        }
        answer = longest.size();
        output();
    }

    public static int bSearchIdx(int target) {
        int s = 0, e = longest.size() - 1;
        while (s < e) {
            int mid = s + (e - s) / 2;
            if (longest.get(mid) < target) s = mid + 1;
            else e = mid;
        }
        return e;
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        br.close();
    }

    public static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
