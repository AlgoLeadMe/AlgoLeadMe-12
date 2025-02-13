//
// Created by 김균호 on 2025. 1. 18..
//
#include <iostream>
#include <vector>
#include <queue>
#include <climits>
using namespace std;

int v, e, s;
int a, b, w;
vector<vector<pair<int, int>>> graph;
priority_queue<pair<int, int>> pq; // { weight, node }
vector<int> dist(v+1, INT_MAX);

// pq의 공간 복잡도는 O(E)
void dijkstra() {
	dist = vector<int>(v+1, INT_MAX);
	dist[s] = 0;
	pq.emplace(0, s);

	while(!pq.empty()) {
		int d = -pq.top().first; // 음수로 저장되어 있음 -> 최소 우선순위 큐
		int cur = pq.top().second; pq.pop();

		if (dist[cur] < d) continue;
		for(auto& [adj, wei] : graph[cur]) {
			//relax
			if (d + wei < dist[adj]) {
				dist[adj] = d + wei;
				pq.emplace(-d - wei, adj);
			}
		}
	}
}

int main() {
	cin >> v >> e >> s;
	graph = vector<vector<pair<int, int>>>(v+1);
	for(int i=0;i<e;i++) {
		cin>>a>>b>>w;
		graph[a].emplace_back(b, w);
	}
	dijkstra();
	for (int i=1;i<dist.size();i++) {
		if (dist[i] == INT_MAX) cout<<"INF\n";
		else cout<<dist[i]<<'\n';
	}
	return 0;
}