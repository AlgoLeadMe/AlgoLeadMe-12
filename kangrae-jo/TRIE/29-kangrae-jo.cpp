#include <string>
#include <vector>
#include <map>

using namespace std;

struct Word {
    char c;
    map<char, Word*> nexts;
    int count = 0;  // 이 노드를 지나간 단어 개수
};

void insert(Word* cur, string word) {
    for (char ch : word) {
        if (cur->nexts.find(ch) == cur->nexts.end()) 
            cur->nexts[ch] = new Word{ch};
        
        cur = cur->nexts[ch];
        cur->count++;
    }
}

int countType(Word* cur, string word) {
    int cnt = 0;
    for (char ch : word) {
        cnt++;
        cur = cur->nexts[ch];
        
        if (cur->count == 1) break; // 유일해지는 순간
    }
    return cnt;
}

int solution(vector<string> words) {
    Word* root = new Word{' '};
    for (const string& word : words)
        insert(root, word);

    int answer = 0;
    for (string word : words)
        answer += countType(root, word);

    return answer;
}