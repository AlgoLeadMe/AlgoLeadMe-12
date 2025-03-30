from itertools import combinations

# 입력 받기
N, M = map(int, input().split())
grid = [list(map(int, input().split())) for _ in range(N)]

houses = []
chickens = []

# 집과 치킨집 좌표 저장
for i in range(N):
    for j in range(N):
        if grid[i][j] == 1:
            houses.append((i, j))
        elif grid[i][j] == 2:
            chickens.append((i, j))

def city_chicken_distance(chosen_chickens):
    total = 0
    for hx, hy in houses:
        # 각 집에 대해 선택된 치킨집들 중 최소 거리를 구함
        min_dist = float('inf')
        for cx, cy in chosen_chickens:
            dist = abs(hx - cx) + abs(hy - cy)
            if dist < min_dist:
                min_dist = dist
        total += min_dist
    return total

# 가능한 모든 치킨집 조합에 대해 도시 치킨거리 합 계산
min_distance = float('inf')
for comb in combinations(chickens, M):
    dist = city_chicken_distance(comb)
    if dist < min_distance:
        min_distance = dist

print(min_distance)