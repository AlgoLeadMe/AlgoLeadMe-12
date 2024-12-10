#include <string>
#include <vector>
#include <queue>

using namespace std;

int mix(int a, int b){
    return a + b*2;
}

int solution(vector<int> scoville, int K) {
    int answer = 0;
    
    // min heap
    priority_queue<int, vector<int>, greater<int>> pq;
    for (int i : scoville){
        pq.push(i);
    }
    
    int foodLast;
    int foodCurrent;
    while (!pq.empty()){
        if (pq.size() == 1) return pq.top() >= K ? answer : -1;
        
        foodLast = pq.top();
        pq.pop();
        foodCurrent = pq.top();
        pq.pop();
        
        if (foodLast >= K) return answer;
        
        pq.push(mix(foodLast, foodCurrent));
        answer++;
    }
    
    return -1;
}
// https://school.programmers.co.kr/learn/courses/30/lessons/42626