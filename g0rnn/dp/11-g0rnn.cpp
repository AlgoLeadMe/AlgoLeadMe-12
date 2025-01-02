//
// Created by 김균호 on 2024. 12. 31..
//
// 1. 조절한 볼륨이 0보다 작거나 max보다 크면 연주할 수 없다 -> -1
// 2. 범위 내의 최댓값을 구하라

#include <iostream>
using namespace std;

int n, s, m;
int v[55];
bool dp[55][1005];
int ans = -1;

int main() {
	cin >> n >> s >> m;
	for (int i = 1; i <= n; i++)
		cin >> v[i];

	if (s + v[1] <= m) dp[1][s + v[1]] = true;
	if (s - v[1] >= 0) dp[1][s - v[1]] = true;

	for (int i = 2; i <= n; i++) {
		for (int j = 0; j <= m; j++) {
			if (!dp[i - 1][j]) continue;
			if (j + v[i] <= m) dp[i][j + v[i]] = true;
			if (j - v[i] >= 0) dp[i][j - v[i]] = true;
		}
	}
	for (int i = m; i >= 0; i--) {
		if (dp[n][i]) {
			ans = i;
			break;
		}
	}
	cout << ans;
	return 0;
}
