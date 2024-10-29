#include <string>
#include <vector>

using namespace std;

int dfs(vector<int>& numbers, int target, int i, int value) {
    if(numbers.size() <= i) {
        return (target == value) ? 1 : 0;
    }
    
    return dfs(numbers, target, i+1, value + numbers[i]) 
        + dfs(numbers, target, i+1, value - numbers[i]);
}

int solution(vector<int> numbers, int target) {
    return dfs(numbers, target, 0, 0);
}


