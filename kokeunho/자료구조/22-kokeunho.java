import java.util.*;

public class Main {
    static int N, K;
    static int[] num;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        num = new int[N];

        for (int i = 0; i < N; i++) {
            num[i] = sc.nextInt();
        }

        K = sc.nextInt();

        System.out.print(findNearest(num, 0, N - 1));

    }
    static int findNearest (int[] arr, int start, int end) {
        int Nearest;

        if (start > end) {
            int left, right;
            left = (start > 0) ? arr[start - 1] : Integer.MIN_VALUE;
            right = (start < arr.length) ? arr[start] : Integer.MAX_VALUE;

            Nearest = (Math.abs(K - left) <= Math.abs(K - right)) ? left : right;
            return Nearest;
        }

        int mid = (end-start)/2 + start;

        if (arr[mid] > K) {
            return findNearest(arr, start, mid - 1);
        } else if (arr[mid] < K) {
            return findNearest(arr, mid + 1, end);
        } else {
            return arr[mid];
        }
    }
}