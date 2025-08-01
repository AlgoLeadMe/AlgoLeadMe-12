#include <iostream>
#include <queue>
#include <vector>

using namespace std;

const int AIR = -1;
const int EMPTY = 0;
const int CHEESE = 1;
const int OFFSET[4][2] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

int N, M;
vector<vector<int>> board;

bool isIn(int y, int x) { return 0 <= y && y < N && 0 <= x && x < M; }

void markAir() {
    vector<vector<bool>> visited(N, vector<bool>(M, false));
    queue<pair<int, int>> q;
    q.push({0, 0});
    visited[0][0] = true;
    board[0][0] = AIR;

    while (!q.empty()) {
        auto [y, x] = q.front();
        q.pop();

        for (int dir = 0; dir < 4; dir++) {
            int y_ = y + OFFSET[dir][0];
            int x_ = x + OFFSET[dir][1];
            if (isIn(y_, x_) && !visited[y_][x_] && board[y_][x_] == EMPTY) {
                visited[y_][x_] = true;
                board[y_][x_] = AIR;
                q.push({y_, x_});
            }
        }
    }
}

bool melt() {
    vector<pair<int, int>> cheeseToMelt;
    for (int y = 0; y < N; y++) {
        for (int x = 0; x < M; x++) {
            if (board[y][x] == CHEESE) {
                int contact = 0;
                for (int dir = 0; dir < 4; dir++) {
                    int y_ = y + OFFSET[dir][0];
                    int x_ = x + OFFSET[dir][1];
                    if (isIn(y_, x_) && board[y_][x_] == AIR) contact++;
                }
                if (contact >= 2) cheeseToMelt.push_back({y, x});
            }
        }
    }
    for (auto [y, x] : cheeseToMelt) board[y][x] = EMPTY;

    return !cheeseToMelt.empty();
}

void resetAir() {
    for (int y = 0; y < N; y++) {
        for (int x = 0; x < M; x++) {
            if (board[y][x] == AIR) board[y][x] = EMPTY;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cout.tie(0);
    cin.tie(0);

    cin >> N >> M;
    board.assign(N, vector<int>(M));
    for (int y = 0; y < N; y++) {
        for (int x = 0; x < M; x++) {
            cin >> board[y][x];
        }
    }

    int answer = 0;
    while (++answer) {
        markAir();           // 바깥 공기 == AIR
        if (!melt()) break;  // 녹일 치즈 없으면 종료
        resetAir();          // AIR = EMPTY 복구 
    }
    cout << answer - 1;

    return 0;
}