#include <string>
#include <vector>
#include <map>

using namespace std;

int solution(vector<vector<int>> points, vector<vector<int>> routes) {
    int answer = 0;

    // 각 시점에서 로봇들의 위치를 기록할 맵 (시간, 좌표) -> 로봇 수
    vector<map<pair<int, int>, int>> history(100005);
    for (auto& route : routes) { 
        int time = 0; 
        int y1 = points[route[0] - 1][0];               // 첫 번째 포인트의 y 좌표
        int x1 = points[route[0] - 1][1];               // 첫 번째 포인트의 x 좌표
        
        history[time][{y1, x1}]++;
        time++;

        for (int i = 0; i < route.size() - 1; i++) {         
            y1 = points[route[i] - 1][0];                // 현재 위치 y 좌표
            x1 = points[route[i] - 1][1];                // 현재 위치 x 좌표
            const int y2 = points[route[i + 1] - 1][0];  // 목표점 y 좌표
            const int x2 = points[route[i + 1] - 1][1];  // 목표점 x 좌표

            // y먼저 이동, 그 뒤에 x이동
            while (y1 != y2 || x1 != x2) {
                if (y1 != y2)  y1 += (y2 > y1 ? 1 : -1); 
                else           x1 += (x2 > x1 ? 1 : -1); 
                
                history[time][{y1, x1}]++;
                time++;
            }
        }
    }

    // 위험한 상황이 발생한 횟수 계산
    for (const auto& historyMap : history) {
        for (const auto& h : historyMap) {
            if (h.second >= 2)  answer++;
        }
    }

    return answer;
}