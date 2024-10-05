#include <iostream>
#include <queue>
#include <vector>

using namespace std;

int M, N;
vector<vector<int> > box;
queue<pair<int, int> > q;

int offset[4][2] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

int move(int x, int y, int dir) {
    int y_ = y + offset[dir][0];
    int x_ = x + offset[dir][1];

    if (x_ < 0 || x_ >= M || y_ < 0 || y_ >= N) return 0;
    if (box[y_][x_] == -1 || box[y_][x_] == 1) return 0;

    if (box[y_][x_] == 0) {
        box[y_][x_] = 1;
        q.push(make_pair(y_, x_));
    }
    return 0;
}

int tomato() {
    int level = -1;

    while (!q.empty()) {
        int size = q.size();
        for (int i = 0; i < size; i++) {
            int y = q.front().first;
            int x = q.front().second;
            q.pop();

            for (int dir = 0; dir < 4; dir++) move(x, y, dir);
        }
        level++;
    }

    return level;
}

bool check() {
    for (int y = 0; y < N; y++)
        for (int x = 0; x < M; x++)
            if (box[y][x] == 0) return false;

    return true;
}

int main() {
    // 1) input
    cin >> M >> N;

    box = vector<vector<int> >(N, vector<int>(M, 0));
    for (int y = 0; y < N; y++) {
        for (int x = 0; x < M; x++) {
            int state;
            cin >> state;

            if (state == 1) q.push(make_pair(y, x));
            box[y][x] = state;
        }
    }

    // 2) solution
    int days = tomato();

    if (check()) cout << days;
    else cout << "-1";

    return 0;
}
