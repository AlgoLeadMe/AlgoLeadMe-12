import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static final int MAX = 18; // 노드는 최대 100,000개
    static int N, M;
    static int[][] parent;
    static int[] depth;
    static List<Integer>[] adj;

    // dfs를 사용하여 2차원 배열 parent를 초기화
    public static void makeTree(int curNode) {
        for (int child : adj[curNode]) {
            if (depth[child] == -1) {
                depth[child] = depth[curNode] + 1;
                parent[child][0] = curNode;
                makeTree(child);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        parent = new int[N][MAX];
        depth = new int[N];
        adj = new ArrayList[N];

        for (int i = 0; i < N; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            String[] input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]) - 1;
            int v = Integer.parseInt(input[1]) - 1;
            adj[u].add(v);
            adj[v].add(u);
        }

        for (int i = 0; i < N; i++) Arrays.fill(parent[i], -1);
        Arrays.fill(depth, -1);
        depth[0] = 0;

        makeTree(0);

        for (int j = 0; j < MAX - 1; j++) {
            for (int i = 1; i < N; i++) {
                if (parent[i][j] != -1)
                    parent[i][j + 1] = parent[parent[i][j]][j];
            }
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            String[] nodePairs = br.readLine().split(" ");
            int u = Integer.parseInt(nodePairs[0]) - 1;
            int v = Integer.parseInt(nodePairs[1]) - 1;

            if (depth[u] < depth[v]) {
                int tmp = u;
                u = v;
                v = tmp;
            }
            int depthDiff = depth[u] - depth[v];

            for (int j = 0; depthDiff > 0; j++) {
                if (depthDiff % 2 == 1) u = parent[u][j];
                depthDiff /= 2;
            }

            if (u != v) {
                for (int j = MAX - 1; j >= 0; j--) {
                    if(parent[u][j] != -1 && parent[u][j] != parent[v][j]) {
                        u = parent[u][j];
                        v = parent[v][j];
                    }
                }
                u = parent[u][0];
            }

            System.out.println(u + 1);
        }

        br.close();
    }
}