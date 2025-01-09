#include <iostream>
#include <queue>

using namespace std;

priority_queue<int> pq;

void base(int gift) {
    int temp;
    while (gift--) {
        cin >> temp;
        pq.push(temp);
    }
}

void meet() {
    if (pq.empty()) cout << -1 << '\n';
    else {
        cout << pq.top() << '\n';
        pq.pop();
    }
}

int main() {
    int n;
    cin >> n;

    int temp;
    while (n--) {
        cin >> temp;

        if (temp == 0) meet();
        else base(temp);
    }

    return 0;
}
