#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

int N, M, K;
vector<vector<char>> board;

int countChanges(char startColor) {
    vector<vector<int>> pSum(N + 1, vector<int>(M + 1, 0));

    // 누적합 배열 계산
    for (int y = 0; y < N; y++) {
        for (int x = 0; x < M; x++) {
            int diff = ((x + y) % 2 == 0) ? (board[y][x] != startColor) : (board[y][x] == startColor);
            pSum[y + 1][x + 1] = pSum[y][x + 1] + pSum[y + 1][x] - pSum[y][x] + diff;
        }
    }

    int result = K * K;
    for (int y = 0; y <= N - K; y++) {
        for (int x = 0; x <= M - K; x++) {
            result = min(result, pSum[y + K][x + K] - pSum[y][x + K] - pSum[y + K][x] + pSum[y][x]);
        }
    }
    return result;
}

int main() {
    cin >> N >> M >> K;

    board = vector<vector<char>>(N, vector<char>(M));
    for (int y = 0; y < N; y++) {
        for (int x = 0; x < M; x++) {
            cin >> board[y][x];
        }
    }

    cout << min(countChanges('B'), countChanges('W')) << '\n';

    return 0;
}