#include <iostream>
#include <regex>
using namespace std;

int main() {
    string sound;
    cin >> sound;

    if (regex_match(sound, regex("(100+1+|01)+")))
        cout << "SUBMARINE\n";
    else
        cout << "NOISE\n";
    return 0;
}