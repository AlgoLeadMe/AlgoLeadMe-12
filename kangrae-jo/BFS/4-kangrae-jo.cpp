#include <queue>
#include <string>
#include <vector>

using namespace std;

#define EMPTY 0
#define PATH 1
#define WALL 2
#define VISITED 3

int offset[4][2] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
int board[102][102] = {EMPTY,};

queue<pair<int, int>> q;

bool check(int cX, int cY, int itemX, int itemY) {
    if (cX == itemX && cY == itemY) return true;
    else return false;
}
bool isMoveable(int cX_, int cY_) {
    if (cX_ < 0 || cX_ > 100 || cY_ < 0 || cY_ > 100) return false;
    if (board[cY_][cX_] == PATH) return true;
    else return false;
}
int move(int cX, int cY, int dir) {
    int cY_ = cY + offset[dir][0];
    int cX_ = cX + offset[dir][1];

    if (isMoveable(cX_, cY_)) q.push(make_pair(cY_, cX_));

    return 0;
}

int solution(vector<vector<int>> rectangle, int characterX, int characterY, int itemX, int itemY) {
    int answer = 0;

    // init
    for (auto& i : rectangle) {
        int x1 = i[0]*2, x2 = i[2]*2;
        int y1 = i[1]*2, y2 = i[3]*2;
        for (int y = y1; y <= y2; y++) {
            for (int x = x1; x <= x2; x++) {
                if (board[y][x] == WALL) continue;
                else if (y == y1 || y == y2 || x == x1 || x == x2) board[y][x] = PATH;
                else board[y][x] = WALL;
            }
        }
    }
    characterX *= 2;
    characterY *= 2;
    itemX *= 2;
    itemY *= 2;
    
    // BFS
    q.push(make_pair(characterY, characterX));

    while (!q.empty()) {
        int size = q.size();

        while (size--){
            int cY = q.front().first;
            int cX = q.front().second;
    
            board[cY][cX] = VISITED;
            q.pop();
    
            if (check(cX, cY, itemX, itemY)) return answer / 2;
    
            for (int dir = 0; dir < 4; dir++) move(cX, cY, dir);
        }
        answer++;
    }

    return answer / 2;
}