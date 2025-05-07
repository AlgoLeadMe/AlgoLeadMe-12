#include <iostream>
#include <queue>

using namespace std;

int main() {
    ios_base::sync_with_stdio(0);
    cout.tie(0);
    cin.tie(0);

    int T;
    cin >> T;

    while (T--) {
        int M, input;
        cin >> M;
        cout << (M + 1) / 2 << '\n';

        priority_queue<int> maxPQ;
        priority_queue<int, vector<int>, greater<>> minPQ;

        for (int i = 1; i <= M; i++) {
            cin >> input;
            maxPQ.push(input);

            if (maxPQ.size() == minPQ.size()) maxPQ.push(input);
            else minPQ.push(input);

            if (!maxPQ.empty() && !minPQ.empty() && (maxPQ.top() > minPQ.top())) {
                maxPQ.push(minPQ.top());
                minPQ.push(maxPQ.top());
                maxPQ.pop();
                minPQ.pop();
            }

            if (i % 2 != 0) cout << maxPQ.top() << " ";
            if (i % 20 == 0) cout << '\n';
        }
    }

    return 0;
}