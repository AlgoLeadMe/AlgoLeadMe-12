//
// Created by 김균호 on 2025. 1. 14..
//
#include <iostream>
using namespace std;

int r, c;
string line;
int answer = 0;
char board[20][20];
bool visited[26];
int offset[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

void backtrack(int x, int y, int cnt) {
	answer = max(answer, cnt);
	for (auto &dir: offset) {
		int nx = x + dir[0];
		int ny = y + dir[1];
		if (0 <= nx && nx < c && 0 <= ny && ny < r && !visited[board[ny][nx] - 'A']) {
			visited[board[ny][nx] - 'A'] = true;
			backtrack(nx, ny, cnt + 1);
			visited[board[ny][nx] - 'A'] = false;
		}
	}
}

int main() {
	cin >> r >> c;
	for (int i = 0; i < r; i++) {
		cin >> line;
		for (int k = 0; k < line.size(); k++) board[i][k] = line[k];
	}
	visited[board[0][0] - 'A'] = true;
	backtrack(0, 0, 1);
	cout << answer;
	return 0;
}
