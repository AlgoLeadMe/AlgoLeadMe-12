#include <algorithm>
#include <iostream>
#include <numeric>
#include <vector>

using namespace std;

struct DSU {
    vector<int> p;
    DSU(int n, int s) : p(n) { iota(p.begin(), p.end(), s); }
    int find(int x) { return p[x] == x ? x : p[x] = find(p[x]); }
    void use(int x) { p[x] = find(x + 1); }
};

int main() {
    ios_base::sync_with_stdio(0);
    cout.tie(0);
    cin.tie(0);

    int N, M, K;
    cin >> N >> M >> K;

    vector<int> blues(M);
    for (int i = 0; i < M; i++) {
        cin >> blues[i];
    }
    sort(blues.begin(), blues.end());

    DSU dsu(M + 1, 0);
    while (K--) {
        int red;
        cin >> red;

        int index = upper_bound(blues.begin(), blues.end(), red) - blues.begin();
        index = dsu.find(index);
        cout << blues[index] << '\n';

        dsu.use(index);
    }

    return 0;
}