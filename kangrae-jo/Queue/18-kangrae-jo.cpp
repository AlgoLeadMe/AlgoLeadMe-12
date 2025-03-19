#include <queue>
#include <vector>

using namespace std;

long long getSum(vector<int>& queue) {
    long long sum = 0;
    for (int q : queue) sum += q;
    return sum;
}

void move(queue<int>& q1, queue<int>& q2, long long& sum1, long long& sum2){
    int num = q1.front();
    q1.pop();
    q2.push(num);
    sum1 -= num;
    sum2 += num;
}

int solution(vector<int> queue1, vector<int> queue2) {
    queue<int> q1, q2;
    for (int num : queue1) q1.push(num);
    for (int num : queue2) q2.push(num);
    
    long long sum1 = getSum(queue1);
    long long sum2 = getSum(queue2);
    
    int len = queue1.size();
    int answer = 0;
    
    while (sum1 != sum2) {
        if (sum1 > sum2) move(q1, q2, sum1, sum2);  
        else move(q2, q1, sum2, sum1);
        
        answer++;
        if (answer > len * 4) return -1;
    }
    return answer;
}