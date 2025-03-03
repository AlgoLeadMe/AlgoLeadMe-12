#include <iostream>
#include <queue>

using namespace std;

int main() {
    int N;
    cin >> N;

    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> pq;
    while (N--) {
        int x;
        cin >> x;

        if (x == 0) {
            if (pq.empty()) cout << 0 << '\n';
            else {
                auto [x_, f_] = pq.top();
                if (f_ < 0) x_ *= -1;
                cout << x_ << '\n';
                pq.pop();
            }
            continue;
        }

        int f = 1;
        if (x < 0) {
            x *= -1;
            f = -1;
        }
        pq.push({x, f});
    }

    return 0;
}