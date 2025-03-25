#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

int moveRight(vector<int>& num, int index, int target) {
    int cnt = 0;
    while (num[index] != target) {
        index = (index + 1) % num.size(); 
        cnt++;
    }
    return cnt;
}

int main() {
    int N, M;
    cin >> N >> M;

    vector<int> num(N);
    for (int i = 0; i < N; i++)  num[i] = i + 1;  

    vector<int> command(M);
    for (int i = 0; i < M; i++) cin >> command[i];  

    int index = 0;  
    int cnt = 0; 
    for (int target : command) {
        int rightCnt = moveRight(num, index, target);
        int leftCnt = num.size() - rightCnt;

        if (rightCnt <= leftCnt) {
            cnt += rightCnt;
            index = (index + rightCnt) % num.size();
        } else {
            cnt += leftCnt;
            index = (index - leftCnt + num.size()) % num.size();
        }

        num.erase(num.begin() + index);
    }

    cout << cnt << endl;

    return 0;
}