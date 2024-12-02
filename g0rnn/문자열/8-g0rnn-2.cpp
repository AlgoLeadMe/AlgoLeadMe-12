#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;

bool compareLength(string a, string b) { return a.length() < b.length(); }

bool isStartWith(const string& str, const string& prefix) {
    return str.compare(0, prefix.length(), prefix) == 0;
}

int main() {
    int n;
    cin >> n;
    vector<string> prefix(n);
    for (int i = 0; i < n; i++) {
        cin >> prefix[i];
    }

    // Sort by string length
    sort(prefix.begin(), prefix.end(), compareLength);

    int answer = n;
    for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
            if (isStartWith(prefix[j], prefix[i])) {
                // 접두사가 있으면 i번째 단어를 없앰
                // 정렬했으니 자신보다 문자열이 긴것만 보면됨
                answer--;
                break;
            }
        }
    }
    cout << answer;
    return 0;
}