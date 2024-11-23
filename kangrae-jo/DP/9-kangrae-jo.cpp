#include <string>
#include <vector>

using namespace std;

int solution(vector<vector<int>> triangle) {        
    int answer = 0;
    
    for (auto& y : triangle) {
        int depth = y.size();
        if (depth == 1) continue;
        
        for (int x=0; x<depth; x++) {
            if (x == 0) y[x] += triangle[depth-2][x];
            else if (x == depth-1) y[x] += triangle[depth-2][depth-2];
            else y[x] += max(triangle[depth-2][x-1], triangle[depth-2][x]);

            answer = max(answer, y[x]);
        }
    }
        
    return answer;
}