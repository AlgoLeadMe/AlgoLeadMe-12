from collections import deque

def solution(storage, requests):
    n = len(storage)
    m = len(storage[0])

    # 0 패딩
    grid = [[0] * (m+2)]
    for row in storage:
        grid.append([0] + list(row) + [0])
    grid.append([0] * (m+2))

    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]

    def bfs():
        visited = [[False] * (m+2) for _ in range(n+2)]
        queue = deque()
        queue.append((0, 0))
        visited[0][0] = True
        while queue:
            x, y = queue.popleft()
            for i in range(4):
                nx = x + dx[i]
                ny = y + dy[i]
                if 0 <= nx < n+2 and 0 <= ny < m+2 and not visited[nx][ny]:
                    if grid[nx][ny] in (0, 1):
                        visited[nx][ny] = True
                        if grid[nx][ny] == 1:
                            grid[nx][ny] = 0
                        queue.append((nx, ny))
    
    for request in requests:
        selected = []

        # 크레인
        if len(request) == 2:
            target = request[0]
            for x in range(1, n+1):
                for y in range(1, m+1):
                    if grid[x][y] == target:
                        selected.append((x, y))

            for x, y in selected:
                grid[x][y] = 1
            
            bfs()

        # 지게차
        elif len(request) == 1:
            target = request
            bfs()
            for i in range(1, n + 1):
                for j in range(1, m + 1):
                    if grid[i][j] == target:
                        for d in range(4):
                            ni = i + dx[d]
                            nj = j + dy[d]
                            if grid[ni][nj] == 0:
                                selected.append((i, j))
                                break
            for x, y in selected:
                grid[x][y] = 0
            

    # 남은물건 개수
    count = 0
    for i in range(1, n + 1):
        for j in range(1, m + 1):
            if grid[i][j] not in (0, 1):
                count += 1

    return count