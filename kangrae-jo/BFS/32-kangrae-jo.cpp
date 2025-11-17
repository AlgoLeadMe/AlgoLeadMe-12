#include <unordered_map>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

const int EMPTY = 0;
const int BLOCK = 1;
const int OFFSET[4][2] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

int N, id = 2;

// 조각 번호 -> 블럭을 이루는 모든 {y,x} * rotate 4개
unordered_map<int, unordered_map<int, vector<pair<int,int>>>> um; 
vector<vector<pair<int, int>>> emptys;

bool isIn(int y, int x) { return 0 <= y && y < N && 0 <= x && x < N; }

vector<vector<int>> rotate(vector<vector<int>>& table) {
    vector<vector<int>> rotated(N, vector<int>(N));
    for (int y = 0; y < N; y++) {
        for (int x = 0; x < N; x++) {
            rotated[x][N - 1 - y] = table[y][x];
        }
    }
    return rotated;
}

void checkBlock(vector<vector<int>>& table, int y, int x, int id, int rot, int target, vector<vector<bool>>& visited) {
    queue<pair<int, int>> q;
    q.push({y, x});
    visited[y][x] = true;
    
    while (!q.empty()) {
        auto[curY, curX] = q.front();
        q.pop();
        
        um[id][rot].push_back({curY - y, curX - x});
        
        for (int dir = 0; dir < 4; dir++) {
            int nextY = curY + OFFSET[dir][0];
            int nextX = curX + OFFSET[dir][1];
            if (isIn(nextY, nextX) && table[nextY][nextX] == target && !visited[nextY][nextX]) {
                q.push({nextY, nextX});
                visited[nextY][nextX] = true;
            }
        }
    }
    sort(um[id][rot].begin(), um[id][rot].end());
}

void checkBlock(vector<vector<int>>& table, int y, int x, int id, int rot) {
    queue<pair<int, int>> q;
    q.push({y, x});
    table[y][x] = id;
    
    while (!q.empty()) {
        auto[curY, curX] = q.front();
        q.pop();
        
        um[id][rot].push_back({curY - y, curX - x});
        
        for (int dir = 0; dir < 4; dir++) {
            int nextY = curY + OFFSET[dir][0];
            int nextX = curX + OFFSET[dir][1];
            if (isIn(nextY, nextX) && table[nextY][nextX] == BLOCK) {
                q.push({nextY, nextX});
                table[nextY][nextX] = id;
            }
        }
    }
    sort(um[id][rot].begin(), um[id][rot].end());
}

void makeBlocks(vector<vector<int>>& table, int rot) {    
    for (int y = 0; y < N; y++) {
        for (int x = 0; x < N; x++) {
            if (table[y][x] == BLOCK) {
                checkBlock(table, y, x, id, rot);
                id++;
            }
        }
    }
}

void makeBlocks_(vector<vector<int>>& table, int rot) {
    vector<vector<bool>> visited(N, vector<bool>(N, false));
    for (int y = 0; y < N; y++) {
        for (int x = 0; x < N; x++) {
            if (table[y][x] >= 2 && !visited[y][x]) {
                checkBlock(table, y, x, table[y][x], rot, table[y][x], visited);
            }
        }
    }
}

void checkEmpty(vector<vector<int>>& board, int y, int x) {
    queue<pair<int, int>> q;
    q.push({y, x});
    board[y][x]++; 
    
    vector<pair<int, int>> temp;
    while (!q.empty()) {
        auto[curY, curX] = q.front();
        q.pop();
        
        temp.push_back({curY - y, curX - x});
        
        for (int dir = 0; dir < 4; dir++) {
            int nextY = curY + OFFSET[dir][0];
            int nextX = curX + OFFSET[dir][1];
            if (isIn(nextY, nextX) && board[nextY][nextX] == EMPTY) {
                q.push({nextY, nextX});
                board[nextY][nextX]++; 
            }
        }
    }
    sort(temp.begin(), temp.end());
    emptys.push_back(temp);
}

void makeEmptys(vector<vector<int>>& board) {    
    for (int y = 0; y < N; y++) {
        for (int x = 0; x < N; x++) {
            if (board[y][x] == EMPTY) {
                checkEmpty(board, y, x);
            }
        }
    }
}

int solution(vector<vector<int>> board, vector<vector<int>> table) {
    N = board.size();
    
    makeBlocks(table, 0);
    for (int rot = 1; rot < 4; rot++) {
        table = rotate(table);
        makeBlocks_(table, rot);
    }
  
    makeEmptys(board);
    
    int answer = 0;
    unordered_map<int, bool> used;
        
    for (vector<pair<int, int>>& empty : emptys) {
        bool flag = false;
        for (int i = 2; i < id; i++) {
            if (used[i]) continue;
            for (int rot = 0; rot < 4; rot++) {
                if (empty == um[i][rot]) {
                    answer += empty.size();
                    used[i] = true;
                    flag = true;
                    break;
                }
            }
            if (flag) break; 
        }
    }
    
    return answer;
}