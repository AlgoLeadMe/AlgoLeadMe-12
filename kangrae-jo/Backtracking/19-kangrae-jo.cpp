#include <unordered_map>
#include <vector>

using namespace std;

vector<vector<int>> candidates;
vector<int> candidate;

int countSameNumber(vector<int>& a, vector<int>& b) {
    unordered_map<int,int> um;
    
    int cnt = 0;
    for (int i : a) um[i]++;
    for (int i : b) if (um[i] != 0) cnt++;
    
    return cnt;
}

void backtracking(int n, int pass) {
    if (candidate.size() == 5) {
        candidates.push_back(candidate);
        return;
    }
    
    for (int i=pass; i<=n; i++) {
        candidate.push_back(i);
        backtracking(n, i+1);
        candidate.pop_back();
    }
}

int solution(int n, vector<vector<int>> q, vector<int> ans) {
    backtracking(n, 1);
    
    int answer = 0;
    for (auto& candi : candidates) {
        int flag = true;
        for (int i=0; i<q.size(); i++) {
            if (countSameNumber(candi, q[i]) != ans[i]) {
                flag = false;
                break;
            }
        }
        if (flag) answer++;
    }
    
    return answer;
}