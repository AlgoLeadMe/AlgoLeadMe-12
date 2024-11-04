#include <iostream>
#include <string>
#include <vector>

using namespace std;

bool isCarpet(int a, int b, int yellow) {
    if ((a - 2) * (b - 2) == yellow) return true;
    else return false;
}

vector<int> solution(int brown, int yellow) {
    vector<int> answer;

    int a, b;
    brown = (brown + 4) / 2;
    for (a = 1; a < brown; a++) 
        if (isCarpet(a, brown - a, yellow)) break;
    
    answer.push_back(b);
    answer.push_back(a);

    return answer;
}

int main() {
    int brown, yellow;
    cin >> brown >> yellow;

    vector<int> answer = solution(brown, yellow);

    cout << answer[0] << " " << answer[1] << endl;

    return 0;
}