#include <iostream>
using namespace std;

int alpha[26] = {
    0,
};
string name;
int n;

// When palindrome is 'ABABA', half is 'ABA' and n.length = 5
void print_palindrome(string half)
{
    cout << half;
    // subtract 1 when it's odd.
    int len = half.length() - n % 2;
    for (int i = len - 1; i >= 0; i--)
    {
        cout << half[i];
    }
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> name;
    n = name.length();

    // init alpha[]
    for (int i = 0; i < n; i++)
    {
        int index = name[i] - 'A';
        alpha[index]++;
    }

    int i;
    string ret = "";
    for (i = 0; i < 26; i++)
    {
        // push each alphabet until possible
        while (alpha[i] > 1)
        {
            ret = ret + (char)(i + 'A');
            alpha[i] -= 2;
        }
    }

    // when length is odd
    if (n % 2 == 1)
    {
        i = 0;
        while (alpha[i] == 0)
            i++;
        ret = ret + (char)(i + 'A');
        alpha[i] -= 1;
    }

    for (i = 0; i < 26; i++)
    {
        if (alpha[i] > 0)
        {
            cout << "I'm Sorry Hansoo\n";
            return 0;
        }
    }

    print_palindrome(ret);
    return 0;
}
