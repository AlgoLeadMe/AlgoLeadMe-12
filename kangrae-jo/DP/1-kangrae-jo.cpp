#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

int solution(vector<int>& s, int n) {
    // maxendpoint
    vector<int> dp(n + 1, 0);
    dp[1] = s[1];

    int maxSum = dp[1];
    for (int i = 2; i <= n; i++) {
        dp[i] = max(dp[i - 1] + s[i], s[i]);
        if (dp[i] > maxSum) maxSum = dp[i];
    }

    return maxSum;
}

int main() {
    int n;
    cin >> n;
    vector<int> s(n+1,0);
    for (int i = 1; i <= n; i++) cin >> s[i];

    cout << solution(s, n);

    return 0;
}