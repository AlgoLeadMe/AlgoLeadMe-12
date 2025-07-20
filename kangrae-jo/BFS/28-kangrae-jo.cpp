#include <queue>
#include <vector>
#include <algorithm>

using namespace std;

const int EMPTY = 0;
const int WALL = 1;
const int OFFSET[4][2] = {{1,0},{0,1},{-1,0},{0,-1}};

struct Pos {
    int x, y, c, d;
    Pos(int a, int b, int c, int d) : x(a), y(b), c(c), d(d) {};
    bool operator< (const Pos& other) const { return this->c > other.c; }
};

bool isIn(int y, int x, int N) { return 0 <= y && y < N && 0 <= x && x < N; }

int solution(vector<vector<int>> board) {
    int N = board[0].size();
    
    vector<vector<vector<int>>> dist(4, vector<vector<int>> (N, vector<int> (N, 1e9)));  // 비용
    priority_queue<Pos> pq;
    pq.push(Pos(0, 0, 0, 0)); dist[0][0][0] = 0;
    pq.push(Pos(0, 0, 0, 1)); dist[1][0][0] = 0;

    while (!pq.empty()) {
        Pos cur = pq.top();
        pq.pop();
        
        if (cur.y == N-1 && cur.x == N-1) continue;
        
        for (int dir = 0; dir < 4; dir++) {
            int y = cur.y + OFFSET[dir][0];
            int x = cur.x + OFFSET[dir][1];
            int c = cur.c + 100;        // 직선
            if (dir != cur.d) c += 500; // 코너
            
            if (!isIn(y, x, N) || board[y][x] == WALL || dist[dir][y][x] <= c) continue;
            
            dist[dir][y][x] = c;
            pq.push(Pos(x, y, c, dir));
        }  
    }
    
    return min({dist[0][N-1][N-1], dist[1][N-1][N-1],
                dist[2][N-1][N-1], dist[3][N-1][N-1]});
}