//
// Created by 김균호 on 2025. 1. 2..
//
#include <iostream>
#include <queue>
#include <vector>
using namespace std;

#define FRIEND 1

int n;
int club[55][55];
vector<int> president;
// 첫번째 인자 기준
//priority_queue<pair<int, int>, vector<pair<int, int> >, greater<> > candidate;

int bfs(int a) {
	queue<int> q;
	vector<bool> visited(n + 1, false);
	int point = -1;
	q.push(a);
	visited[a] = true;

	while (!q.empty()) {
		size_t size = q.size();

		for (int _ = 0; _ < size; _++) {
			int k = q.front();
			q.pop();
			for (int i = 1; i <= n; i++) {
				if (k != i && !visited[i] && club[k][i] == FRIEND) {
					visited[i] = true;
					q.push(i);
				}
			}
		}
		point++;
	}
	return point;
}

int main() {
	cin >> n;
	int a, b;
	while (true) {
		cin >> a >> b;
		if (a == -1 && b == -1) break;
		club[a][b] = FRIEND;
		club[b][a] = FRIEND;
	}
	/*
	for (int i = 1; i <= n; i++) {
		int score = bfs(i);
		candidate.emplace(score,i);
	}

	auto [point, who] = candidate.top();
	candidate.pop();
	president.push_back(who);

	while (candidate.top().first == point) {
		president.push_back(candidate.top().second);
		candidate.pop();
	}*/

	int min_point = INT_MAX;
	for (int i = 1; i <= n; i++) {
		int score = bfs(i);
		if (score < min_point) {
			min_point = score;
			president.clear();
			president.push_back(i);
		} else if (score == min_point) {
			president.push_back(i);
		}
	}

	cout << min_point << ' ' << president.size() << '\n';
	for (int i: president) cout << i << ' ';
	return 0;
}
