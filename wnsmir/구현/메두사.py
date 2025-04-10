from collections import deque

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

def bfs(start_x, start_y, grid, N):
    queue = deque()
    queue.append((start_x, start_y))
    visited = [[False]*N for _ in range(N)]
    distance = [[0]*N for _ in range(N)]

    visited[start_x][start_y] = True
    distance[start_x][start_y] = 0

    while queue:
        x, y = queue.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
                
            if 0 <= nx < N and 0 <= ny < N:
                if not visited[nx][ny] and grid[nx][ny] == 0:
                    visited[nx][ny] = True
                    queue.append((nx, ny))
                    distance[nx][ny] = distance[x][y] + 1

    return distance

def move_medusa(x, y, distance, N):
    for i in range(4):
        cx = x + dx[i]
        cy = y + dy[i]
        if 0 <= cx < N and 0 <= cy < N:
            if distance[cx][cy] == distance[x][y] - 1:
                x, y = cx, cy
                break

    return x, y

#입력
N = int(input())
sr, sc, er, ec = map(int, input().split())
grid = []
for _ in range(N):
    grid.append(list(map(int, input().split())))

distance = bfs(er, ec, grid, N)

current_x, current_y = sr, sc

while True:
    current_x, current_y = move_medusa(current_x, current_y, distance, N)
    if current_x == er and current_y == ec:
        print(0)
        break