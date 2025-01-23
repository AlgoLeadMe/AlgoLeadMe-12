#include <string>
#include <vector>

using namespace std;

struct Time {
    int m, s;
};

string solution(string video_len, string pos, string op_start, string op_end, vector<string> commands) {
    Time video_len_, pos_, op_start_, op_end_;

    // string to int (time)
    video_len_.m = (video_len[0] - '0') * 10 + (video_len[1] - '0');
    video_len_.s = (video_len[3] - '0') * 10 + (video_len[4] - '0');
    pos_.m = (pos[0] - '0') * 10 + (pos[1] - '0');
    pos_.s = (pos[3] - '0') * 10 + (pos[4] - '0');
    op_start_.m = (op_start[0] - '0') * 10 + (op_start[1] - '0');
    op_start_.s = (op_start[3] - '0') * 10 + (op_start[4] - '0');
    op_end_.m = (op_end[0] - '0') * 10 + (op_end[1] - '0');
    op_end_.s = (op_end[3] - '0') * 10 + (op_end[4] - '0');

    // 오프닝 건너뛰기
    if (op_start_.m * 60 + op_start_.s <= pos_.m * 60 + pos_.s &&
        op_end_.m * 60 + op_end_.s > pos_.m * 60 + pos_.s) {
        pos_.m = op_end_.m;
        pos_.s = op_end_.s;
    }

    // command 실행
    for (string command : commands) {
        if (command == "next") {
            pos_.s += 10;
            if (pos_.s >= 60) {
                pos_.s -= 60;
                pos_.m += 1;
            }
            if (video_len_.m * 60 + video_len_.s <= pos_.m * 60 + pos_.s) {
                pos_.m = video_len_.m;
                pos_.s = video_len_.s;
            }
        } 
        else if (command == "prev") {
            pos_.s -= 10;
            if (pos_.s < 0) {
                pos_.s += 60;
                pos_.m -= 1;
            }
            if (pos_.m < 0) {
                pos_.m = 0;
                pos_.s = 0;
            }
        }
        // 오프닝 건너뛰기
        if (op_start_.m * 60 + op_start_.s <= pos_.m * 60 + pos_.s &&
            op_end_.m * 60 + op_end_.s > pos_.m * 60 + pos_.s) {
            pos_.m = op_end_.m;
            pos_.s = op_end_.s;
        }
    }

    string answer = "";
    if (pos_.m < 10) answer += "0";
    answer += to_string(pos_.m) + ":";
    if (pos_.s < 10) answer += "0";
    answer += to_string(pos_.s);

    return answer;
}