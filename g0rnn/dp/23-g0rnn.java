import java.util.*;
import java.io.*;

public class Main {

    static int answer, N;
    static int[] dpNone;
    static int[][] dpLion;

    public static void main(String[] args) throws IOException{
        input();
        dpLion[0][0] = dpLion[0][1] = 1;
        dpNone[0] = 1;

        for (int i = 1; i < N; i++) {
            dpLion[i][0] = (dpLion[i-1][1] + dpNone[i-1]) % 9901;
            dpLion[i][1] = (dpLion[i-1][0] + dpNone[i-1]) % 9901;
            dpNone[i] = (dpLion[i-1][0] + dpLion[i-1][1] + dpNone[i-1]) % 9901;
        }
        answer = (dpNone[N-1] + dpLion[N-1][0] + dpLion[N-1][1]) % 9901;
        output();
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dpNone = new int[N];
        dpLion = new int[N][2];
        br.close();
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}
