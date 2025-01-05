//
// Created by 김균호 on 2025. 1. 5..
//
#include <iostream>
#include <climits>
#include <vector>
using namespace std;

int n, minDiff = INT_MAX;
int s[25][25];
vector<bool> visited;

void sumPoint() {
	int start = 0, link = 0;
	for (int i = 1; i <= n; i++) {
		for (int j = i + 1; j <= n; j++) {
			if (visited[i] && visited[j]) start += s[i][j] + s[j][i];
			else if (!visited[i] && !visited[j]) link += s[i][j] + s[j][i];
		}
	}
	minDiff = min(minDiff, abs(start - link));
}

// 임의의 n/2개를 뽑음
void dfs(int cur, int cnt) {
	if (cnt == n / 2) {
		sumPoint();
		return;
	}
	for (int i = cur; i <= n; i++) {
		if (!visited[i]) {
			visited[i] = true;
			dfs(i + 1, cnt + 1);
			visited[i] = false;
		}
	}
}

int main() {
	cin >> n;
	for (int y = 1; y <= n; y++) {
		for (int x = 1; x <= n; x++) {
			cin >> s[y][x];
		}
	}
	visited = vector<bool>(n + 1, false);
	dfs(1, 0);
	cout << minDiff;
	return 0;
}
