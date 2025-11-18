#include <vector>

using namespace std;

const int PATH = 0;
const int WALL = 1;
const int FIX  = 2;
const int MOD = 20170805;

int solution(int m, int n, vector<vector<int>> board) {
    vector<vector<int>> down (m, vector<int> (n, 0));
    vector<vector<int>> right(m, vector<int> (n, 0));
    
    for (int y = 0; y < m; y++) {
        if (board[y][0] == WALL) break;
        down[y][0] = 1;
    }
    for (int x = 0; x < m; x++) {
        if (board[0][x] == WALL) break;
        right[0][x] = 1;
    }
    
    for (int y = 1; y < m; y++) {
        for (int x = 1; x < n; x++) {
            // 진입 불가
            if (board[y][x] == WALL) continue;
            
            // 위에서 아래로
            if (board[y - 1][x] == FIX) down[y][x] = down[y - 1][x];
            else down[y][x] = (down[y - 1][x] + right[y - 1][x]) % MOD; 
            
            // 왼쪽에서 오른쪽으로
            if (board[y][x - 1] == FIX) right[y][x] = right[y][x - 1];
            else right[y][x] = (down[y][x - 1] + right[y][x - 1]) % MOD; 
        }
    }
    
    return (down[m - 1][n - 1] + right[m - 1][n - 1]) % MOD;
}
