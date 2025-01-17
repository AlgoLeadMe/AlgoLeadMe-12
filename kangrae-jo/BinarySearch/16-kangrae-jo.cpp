#include <string>
#include <vector>

using namespace std;

int solution(vector<int> diffs, vector<int> times, long long limit) {
    long long start = 0;
    long long end = limit;
    long long level;
    
    while (1){
        long long time = 0;
        
        level = (start + end) / 2;
        if (start > end) break;

        for (int i = 0; i < diffs.size(); i++){
            long long prev = level - diffs[i];
            if (prev < 0) time += -prev * (times[i-1] + times[i]);
            time += times[i];
        }
        
        if (time > limit) start = level + 1;
        else if (time <= limit) end = level - 1;
    }
    
    return level + 1;
}