#include <iostream>
#include <vector>

using namespace std;

int main() {
    ios_base::sync_with_stdio(0);
    cout.tie(0);
    cin.tie(0);

    int N, M;
    cin >> N >> M;

    vector<int> num(N + 1, 0);
    int temp, sum =0;
    for (int i = 1; i <= N; i++) {
        cin >> temp;
        sum += temp;
        num[i] = sum;
    }

    while (M--) {
        int i, j;
        cin >> i >> j;

        cout << num[j] - num[i-1] << "\n";
    }

    return 0;
}