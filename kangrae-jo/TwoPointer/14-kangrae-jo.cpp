#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

int main() {
    int N;
    cin >> N;

    vector<long int> have(N);
    for (int i = 0; i < N; i++) {
        cin >> have[i];
    }
    sort(have.begin(), have.end());

    int left = 0, right = N - 1;
    long int mix = have[left] + have[right];

    int pLeft = left, pRight = right;
    long int pMix = mix;

    while (left < right) {
        long int mix = have[left] + have[right];
        if (mix == 0) {
            cout << have[left] << " " << have[right];
            return 0;
        }
        else if (abs(mix) < abs(pMix)) {
            pMix = mix;
            pLeft = left;
            pRight = right;
        }
        if (mix > 0) right--;
        else left++;
    }
    cout << have[pLeft] << " " << have[pRight];

    return 0;
}
