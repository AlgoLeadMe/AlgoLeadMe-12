#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

int T, N, M;
vector<int> A, B;

void input(int& n, vector<int>& v) {
    cin >> n;
    v.assign(n, 0);
    for (int i = 0; i < n; i++) {
        cin >> v[i];
    }
}

vector<int> makeSub(int n, vector<int> v) {
    vector<int> sub;
    for (int i = 0; i < n; i++) {
        int sum = 0;
        for (int j = i; j < n; j++) {
            sum += v[j];
            sub.push_back(sum);
        }
    }
    return sub;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    // 0) input
    cin >> T;
    input(N, A);
    input(M, B);

    // 1) A, B 모든 부분합
    vector<int> subA = makeSub(N, A);
    vector<int> subB = makeSub(M, B);

    // 2) 정렬
    sort(subA.begin(), subA.end());
    sort(subB.begin(), subB.end());

    // 3) 투포인터로 합이 T인 쌍 세기
    long long answer = 0;
    int left = 0, right = subB.size() - 1;
    while (left < subA.size() && 0 <= right) {
        int sum = subA[left] + subB[right];
        if (sum == T) {
            long long countA = 1, countB = 1;
            while (left + 1 < subA.size() && subA[left] == subA[left + 1]) {
                countA++;
                left++;
            }
            while (right - 1 >= 0 && subB[right] == subB[right - 1]) {
                countB++;
                right--;
            }
            answer += countA * countB;
            left++;
            right--;
        } 
        else if (sum < T) left++;
        else right--;
    }

    cout << answer << '\n';
    
    return 0;
}