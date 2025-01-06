#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    int N;
    cin >> N;

    vector<int> ATM(N);
    for (int i = 0; i < N; i++) {
        cin >> ATM[i];
    }

    sort(ATM.begin(), ATM.end());

    int result = 0;
    for (int wait = 0; wait < N; wait++) {
        for (int use = 0; use <= wait; use++) {
            result += ATM[use];
        }
    }

    cout << result;

    return 0;
}