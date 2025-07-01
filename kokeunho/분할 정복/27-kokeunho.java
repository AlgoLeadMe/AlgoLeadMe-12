import java.util.*;

public class Main {
    static int N, r, c;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        r = sc.nextInt();
        c = sc.nextInt();

        System.out.print(moveZ(N, r, c));
    }
    static int moveZ(int n, int x, int y) {
        if (n == 0) return 0;
        /*사분면 한 변의 길이 = 2 ^ (N-1)*/
        int sideLen = 1 << (n - 1);
        int size = sideLen * sideLen;

        if (x < sideLen && y < sideLen) {
            return moveZ(n-1, x, y);
        } else if (x < sideLen && y >= sideLen) {
            return size + moveZ(n-1, x, y-sideLen);
        } else if (x >= sideLen && y < sideLen) {
            return 2 * size + moveZ(n-1, x-sideLen, y);
        } else {
            return 3 * size + moveZ(n-1, x-sideLen, y-sideLen);
        }
    }
}
