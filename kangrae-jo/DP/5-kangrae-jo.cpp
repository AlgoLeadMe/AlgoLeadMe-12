#include <iostream>
#include <algorithm>

using namespace std;

int field[1001][1001];
int maxX=0, maxY=0;
int minX=1001, minY=1001;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	
	int N, K;
	cin >> N >> K;
	
	int x1,y1,x2,y2;

	for (int i=0; i<N; i++){
		cin >> x1 >> y1 >> x2 >> y2;

		
		minX=min(minX,x1);
		minY=min(minY,y1);
		maxX=max(maxX,x2);
		maxY=max(maxY,y2);
		
		field[y1][x1] += 1;
		field[y1][x2] -= 1;
		field[y2][x1] -= 1;
		field[y2][x2] += 1;
	}
	
	for(int y=minY; y<=maxY; y++)
		for (int x=minX; x<=maxX; x++)
			field[y][x+1] += field[y][x];

	for (int x=minX; x<=maxX; x++)
		for(int y=minY; y<=maxY; y++)
			field[y+1][x] += field[y][x];
	
	int result = 0;
	for(int y=minY; y<=maxY; y++)
		for (int x=minX; x<=maxX; x++)
			if (field[y][x] == K) result++;
	
	cout << result;

	return 0;
}