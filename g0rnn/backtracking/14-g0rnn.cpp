//
// Created by 김균호 on 2025. 1. 7..
//
#include <iostream>
using namespace std;

#define EMPTY -1

int n;
int chess[15] = {0};
// 배열의 value은 퀸이 놓여진 row를 의미
// 배열의 index는 퀸이 놓여진 column을 의미
// ex) chess[3] = 5 -> 퀸이 5행 3열에 놓여져 있다는 의미

// col과 row는 퀸을 놓을 열과 행
// col과 row로 받은 위치에 대해 이미 놓여진 퀸을 피해 놓을 수 있는지
bool canMoveTo(int col, int row) {
	for (int i = 0; i < n; i++) {
		if (chess[i] == EMPTY) continue;
		if (abs(col - i) == row - chess[i]) return false; // 대각선인 경우
		if (i == col) return false; // 같은 열인 경우
	}
	return true;
}

int findQueen(int cnt, int row) {
	if (cnt == n) return 1;

	int total = 0;
	for (int col = 0; col < n; col++) {
		if (!canMoveTo(col, row)) continue;
		chess[col] = row;
		total += findQueen(cnt + 1, row + 1); // 가능한 경우의 수의 총합
		chess[col] = EMPTY;
	}
	return total;
}

int main() {
	cin >> n;
	fill(chess, chess + n, EMPTY);
	cout << findQueen(0, 0);
	return 0;
}
