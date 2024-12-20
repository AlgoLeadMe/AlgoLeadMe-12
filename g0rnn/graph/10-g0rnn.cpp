//
// Created by 김균호 on 2024. 12. 20..
//
#include <iostream>
#include <unordered_set>
#include <vector>
using namespace std;

const int SANG = 1;
int n, m;
int ret = 0;
vector<unordered_set<int> > relationship;
unordered_set<int> members = {1};

int main() {
	cin >> n >> m;
	relationship = vector<unordered_set<int> >(n + 1);
	int a, b;

	for (int i = 0; i < m; i++) {
		cin >> a >> b;
		//
		if (a == SANG) members.insert(b);
		// make a graph
		relationship[a].insert(b);
		relationship[b].insert(a);
	}

	// find friends' friends
	for (auto f: relationship[SANG]) {
		for (auto r: relationship[f]) {
			members.insert(r);
		}
	}

	// exclude SANG
	cout << members.size() - 1;
}
