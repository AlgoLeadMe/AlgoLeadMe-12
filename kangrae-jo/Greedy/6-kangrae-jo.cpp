#include <algorithm>
#include <vector>

using namespace std;

int solution(vector<vector<int>> routes) {
    int answer = 1;
    sort(routes.begin(), routes.end());

    int temp = routes[0][1];
    for (auto& route : routes) {
        int in = route[0];
        int out = route[1];
        
        if (temp < in) {
            answer++;
            temp = out;
        }
        if (temp >= out)
            temp = out;
    }

    return answer;
}