#include <iostream>
#include <vector>

using namespace std;

const int INF = 1e9;

bool solution(vector<vector<pair<int,int>>>& graph, int N) {
    vector<int> dist(N + 1, 0);         
    for (int i = 1; i <= N; i++) {
        bool updated = false;
        for (int from = 1; from <= N; from++) {
            for (auto [to, cost] : graph[from]) {
                if (dist[to] > dist[from] + cost) {
                    dist[to] = dist[from] + cost;
                    updated = true;
                    if (i == N) return true;
                }
            }
        }
        if (!updated) break;   
    }

    return false;
}

int main() {
    int TC;
    cin >> TC;

    while (TC--) {
        int N, M, W;
        cin >> N >> M >> W;

        vector<vector<pair<int, int>>> graph(N + 1);
        for (int i = 0; i < M; i++) {
            int S, E, T;
            cin >> S >> E >> T;

            graph[S].push_back({E, T});
            graph[E].push_back({S, T});
        }

        for (int i = 0; i < W; i++) {
            int S, E, T;
            cin >> S >> E >> T;

            graph[S].push_back({E, -T});
        }

        if (solution(graph, N)) cout << "YES\n";
        else cout << "NO\n";
    }

    return 0;
}
