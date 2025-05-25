import java.util.*;

public class Main {
    static int N;
    static long B;
    static int[][] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        B = sc.nextLong();
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int[][] result = matrixPower(arr, B);

        StringBuilder sb = new StringBuilder();
        for (int[] row : result) {
            for (int value : row) {
                sb.append(value).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
    static int[][] matrixPower(int[][] matrix, long count) {
        if (count == 1L) {
            int[][] result = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    result[i][j] = matrix[i][j] % 1000;
                }
            }
            return result;
        }

        int[][] half = matrixPower(matrix, count/2);
        int[][] result = matrixMultiply(half, half);

        if (count % 2 != 0) {
            result = matrixMultiply(result, matrix);
        }

        return result;
    }
    static int[][] matrixMultiply(int[][] arr1, int[][] arr2) {
        int[][] resultMatrix = new int[N][N];

        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum = 0;
                for (int k = 0; k < N; k++) {
                    sum += arr1[i][k] * arr2[k][j];
                }
                resultMatrix[i][j] = sum % 1000;
            }
        }

        return resultMatrix;
    }
}
