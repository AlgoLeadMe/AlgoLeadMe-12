#include <vector>
#include <algorithm>

using namespace std;

bool isPossible(long long time, int G, int S, vector<int>& g, vector<int>& s, vector<int>& w, vector<int>& t) {
    long long totalSum = 0, totalG = 0, totalS = 0;

    for (int i = 0; i < g.size(); i++) {
        long long count = time / (2 * t[i]);
        if (time % (2 * t[i]) >= t[i]) count++;

        long long sum = count * w[i];
        totalSum += min(sum, (long long)g[i] + s[i]);
        totalG   += min(sum, (long long)g[i]);
        totalS   += min(sum, (long long)s[i]);

        if (totalSum >= G + S && totalG >= G && totalS >= S) return true;
    }

    return totalSum >= G + S && totalG >= G && totalS >= S;
}

long long solution(int G, int S, vector<int> g, vector<int> s, vector<int> w, vector<int> t) {
    long long start = 0;
    long long end = 1e9 * 1e5 * 1e2;

    while (start <= end) {
        long long mid = (start + end) / 2;
        if (isPossible(mid, G, S, g, s, w, t)) end = mid - 1;
        else start = mid + 1;
    }

    return start;
}