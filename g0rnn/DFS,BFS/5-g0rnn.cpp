#include <iostream>
#include <vector>
#include <queue>
#include <climits>

#define EMPTY 0
#define WALL 1
#define VIRUS 2
#define VISITED 3

using namespace std;

int n, m;
int safe = 0, virus = 0;
int maxSafe = INT_MIN;
vector<vector<int>> lab;
queue<pair<int, int>> q;
int offset[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

bool canMove(const vector<vector<int>> &tmp, int x, int y)
{
    return x >= 0 && x < m && y >= 0 && y < n && tmp[y][x] == EMPTY;
}

void bfs()
{
    int all_virus = 0;
    vector<vector<int>> tmp_lab = lab;
    queue<pair<int, int>> tmpq = q;
    while (!tmpq.empty())
    {
        int x = tmpq.front().first;
        int y = tmpq.front().second;
        all_virus++;
        tmpq.pop();

        for (int dir = 0; dir < 4; dir++)
        {
            int nx = x + offset[dir][0];
            int ny = y + offset[dir][1];
            if (canMove(tmp_lab, nx, ny))
            {
                tmp_lab[ny][nx] = VISITED;
                tmpq.push(make_pair(nx, ny));
            }
        }
    }

    // 큐에서 모든 바이러스를 검사한다. 따라서 all_virus를 빼면 기존 virus를 다시 더해야한다.
    // 따라서 empty의 개수 - 추가한 wall개수 - 모든 virus 수 + 기존 virus 수
    maxSafe = max(maxSafe, safe - 3 - all_virus + virus);
}

void dfs(int wall)
{
    if (wall == 3)
    {
        bfs();
        return;
    }

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (lab[i][j] == EMPTY)
            {
                lab[i][j] = WALL;
                dfs(wall + 1);
                lab[i][j] = EMPTY;
            }
        }
    }
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n >> m;
    lab = vector<vector<int>>(n, vector<int>(m));
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> lab[i][j];
            if (lab[i][j] == VIRUS)
            {
                q.push(make_pair(j, i)); // (x, y) 꼴로 저장
                virus++;
            }
            if (lab[i][j] == EMPTY)
                safe++;
        }
    }

    dfs(0);
    cout << maxSafe;

    return 0;
}