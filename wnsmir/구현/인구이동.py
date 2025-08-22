from collections import deque

N, L, R = map(int, input().split())

# 지도 받기
land = [list(map(int, input().split())) for _ in range(N)]
move = False
dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]
day = 0

while True:
    day += 1
    unify = []
    visited = [[False]*N for _ in range(N)]

    for i in range(N):
        for j in range(N):
            # 이미 방문한곳이라면 pass
            if visited[i][j] == True:
                continue

            start = (i, j)
            queue = deque()
            queue.append(start)
            uni = []
            while queue:
                x, y = queue.popleft()
                for d in range(4):
                    nx = x + dx[d]
                    ny = y + dy[d]
                    if 0 <= nx < N and 0 <= ny < N:
                        if visited[nx][ny] == False:
                            # 차이가 L이상 R이하라면 추가
                            if L <= abs(land[x][y] - land[nx][ny]) <= R:
                                visited[nx][ny] = True
                                queue.append((nx, ny))
                                uni.append((nx, ny))

            # 이번턴에 생긴 덩어리들 추가
            if len(uni) > 0:
                unify.append(uni)
    
    # 인구이동이 진행되는 경우 인구이동 진행
    if len(unify) > 0:
        # 인구이동 진행
        for uni in unify:
            e_value = 0
            for x, y in uni:
                value = land[x][y] 
                e_value += value
            
            e_count = len(uni)
            population = e_value//e_count
            for u in uni:
                x, y = u
                land[x][y] = population

    # 안되는 경우 while문 종료하고 해당 일자 print
    else:
        print(day - 1)
        break