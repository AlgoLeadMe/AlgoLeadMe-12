#include <iostream>
#include <list>
#include <vector>

using namespace std;

int N, M;
vector<bool> visited;
vector<list<int>> graph;

void dfs(int v, list<int>& r) {
    visited[v] = true;
    for (int x : graph[v]) {
        if (visited[x] == false) dfs(x, r);
    }
    r.push_front(v);
}

list<int> topologicalSort() {
    list<int> r;
    for (int v = 1; v <= N; v++) {
        if (visited[v] == false) dfs(v, r);
    }

    return r;
}

int main() {
    cin >> N >> M;
    
    visited = vector<bool>(N + 1, false);
    graph = vector<list<int>>(N + 1);

    while (M--) {
        int a, b;
        cin >> a >> b;

        graph[a].push_back(b);
    }

    list<int> r = topologicalSort();
    for (int i : r) {
        cout << i << " ";
    }

    return 0;
}
