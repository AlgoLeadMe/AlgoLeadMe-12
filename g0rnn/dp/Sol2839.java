import java.util.*;

public class Sol2839 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] dp = new int[5001];

        dp[0] = dp[1] = dp[2] = dp[4] = Integer.MAX_VALUE;
        dp[3] = 1;
        dp[5] = 1;

        for(int i = 6; i <= n; i++) {
            if(dp[i-3] == Integer.MAX_VALUE && dp[i-5] == Integer.MAX_VALUE)
                dp[i] = Integer.MAX_VALUE;
            else
                dp[i] = Integer.min(dp[i-3], dp[i-5]) + 1;
        }
        System.out.println((dp[n] == Integer.MAX_VALUE) ? -1 : dp[n]);
        sc.close();
    }
}
