#include <iostream>
#include <string>
using namespace std;

bool is_end_num(int end_num)
{
    string num = to_string(end_num);
    int check = 0;
    for (int i = 0; i < num.length() - 2; i++)
        if (num[i] == '6' && num[i + 1] == '6' && num[i + 2] == '6')
            return true;

    return false;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int end_num = 666;
    int cnt, ctr = 1;
    cin >> cnt;
    while (ctr != cnt)
    {
        end_num++;
        if (is_end_num(end_num))
            ctr++;
    }
    cout << end_num;
    return 0;
}