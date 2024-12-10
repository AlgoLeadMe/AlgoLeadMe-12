//
// Created by 김균호 on 2024. 12. 4..
//
#include <iostream>
using namespace std;

int a, b, answer = 1;

int main() {
	cin >> a >> b;

	while (a < b) {
		if (b % 2 == 0) {
			b = b / 2;
		} else if (b % 10 == 1) {
			b /= 10;
		} else {
			break;
		}
		answer++;
	}

	if(a == b) cout << answer;
	else cout << -1;

	return 0;
}