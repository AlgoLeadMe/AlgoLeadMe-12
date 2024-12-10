#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>
#include <cmath>
using namespace std;

// 2,100,000,000
const int MAX = 500005;
int n;
vector<int> arr;

int getMode()
{
    if (n == 1)
        return arr[0];
    int mode = 0;
    int sec_mode = 0;
    int tmp_mode = INT_MIN;
    int freq = 0;
    int tmp = 0;
    bool flag = false; // 두번째로 작은 최빈값을 찾았는지

    // 제일 작은 최빈값.
    for (int i = 0; i < n - 1; i++)
    {
        if (arr[i] == arr[i + 1])
        {
            tmp++;
        }
        else
        {
            tmp += 1;

            // 빈도가 더 많으면 최대 빈도와 최빈값를 교체
            if (freq < tmp)
            {
                // 이 부분이 교체되면 항상 가장 착은 최빈값을 찾았다는 것
                freq = tmp;
                mode = arr[i];
                flag = false;
            }
            // 맨 처음 중복되는 빈도값을 찾을 때 sec_mode를 설정한다.
            // 이때 arr[i]는 이미 구했던 mode보다 무조건 크다.(정렬된 배열)
            else if (freq == tmp && flag == false)
            {
                sec_mode = arr[i];
                flag = true; // 중복되는 빈도 중 두번째로 작은 최빈값을 구했다는 표시
            }
            tmp = 0;
        }
    }

    // 맨 뒷 부분에 최빈값들이 있을 때
    if (tmp > 0)
    {
        tmp += 1;
        if (tmp > freq)
        {
            freq = tmp;
            mode = arr[n - 1];
            flag = false;
        }
        else if (tmp == freq && flag == false)
        {
            sec_mode = arr[n - 1];
            flag = true;
        }
    }

    return (flag ? sec_mode : mode);
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n;
    arr = vector<int>(n, 0);

    int sum = 0;
    int tmp = 0;
    int freq = 0;

    for (int i = 0; i < n; i++)
    {
        cin >> arr[i];
        sum += arr[i];
    }
    sort(arr.begin(), arr.end());

    int mode = getMode();

    double avg = (double)sum / n;
    avg = round(avg);
    avg = (avg == -0) ? 0 : avg;

    int range = arr[n - 1] - arr[0];

    std::cout << avg << '\n';
    std::cout << arr[n / 2] << '\n';
    std::cout << mode << '\n';
    std::cout << range;

    return 0;
}