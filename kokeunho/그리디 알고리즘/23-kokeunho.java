//컴퓨터공학과 고근호 202011302
import java.io.*;

public class Main {
    static int N, W;
    static int[] w, v, c;
    static int[] selected;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/file/input10.txt"));

        N = Integer.parseInt(br.readLine().trim());
        w = new int[N];
        v = new int[N];
        c = new int[N];

        for (int i = 0; i < N; i++) {
            String[] product = br.readLine().trim().split(" ");
            w[i] = Integer.parseInt(product[0]);
            v[i] = Integer.parseInt(product[1]);
            c[i] = Integer.parseInt(product[2]);
        }

        W = Integer.parseInt(br.readLine().trim());
        selected = new int[N];

        selectProduct(0, 0, 0, 0);

        System.out.print(result);
    }
    static void selectProduct(int index, int weight, int value, int cost) {
        if (weight > W) return;

        if (index == N) {
            result = Math.max(result, value - cost);
            return;
        }

        selected[index] = 0;
        selectProduct(index + 1, weight, value, cost + c[index]);
        selected[index] = 1;
        selectProduct(index + 1, weight + w[index], value + v[index], cost);
    }
    /**static void selectProduct(int index) {
        if (index == N) {
            if (calTotalWeight() <= W) {
                int newMaxValue = calResult();
                result = Math.max(result, newMaxValue);
            }
            return;
        }

        selected[index] = 0;
        selectProduct(index + 1);
        selected[index] = 1;
        selectProduct(index + 1);
    }
    static int calResult() {
        int totalValue = 0, totalCost = 0;

        for (int i = 0; i < N; i++) {
            if (selected[i] == 1) {
                totalValue += v[i];
            } else {
                totalCost += c[i];
            }
        }
        return totalValue - totalCost;
    }
    static int calTotalWeight() {
        int totalWeight = 0;

        for (int i = 0; i < N; i++) {
            if (selected[i] == 1) {
                totalWeight += w[i];
            }
        }
        return totalWeight;
    } **/
}