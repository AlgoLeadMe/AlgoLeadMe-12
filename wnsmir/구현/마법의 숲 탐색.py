from collections import deque

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

def BFS(current_x, current_y, grid, exit, i, R, C):
    queue = deque()
    visited = [[False] * C for _ in range(R)]
    visited[current_x][current_y] = True
    queue.append((current_x, current_y, grid[current_x][current_y]))  # (x, y, 기준 번호)

    while queue:
        x, y, cur_num = queue.popleft()
        for j in range(4):
            nx = x + dx[j]
            ny = y + dy[j]
            if 0 <= nx < R and 0 <= ny < C and not visited[nx][ny]:
                next_val = grid[nx][ny]
                # 1. 같은 자취 번호는 그대로 따라가기
                if next_val == cur_num:
                    visited[nx][ny] = True
                    queue.append((nx, ny, cur_num))
                # 2. 출구를 통해 다른 자취로 넘어가는 경우
                elif next_val != 0 and exit[x][y] == 1:
                    visited[nx][ny] = True
                    queue.append((nx, ny, next_val))  # 기준 번호 바꾸기
    return visited

def score(current_x, current_y, grid, exit, R, i, C):
    visited = BFS(current_x, current_y, grid, exit, i, R, C)
    max_row = 0
    for row_idx in range(R):
        if any(visited[row_idx]):
            max_row = row_idx
    return max_row

def is_out_of_bounds(x, y, R, C):
    return not (1 <= x < R-1 and 1 <= y < C-1)

R, C, K = map(int, input().split())
gollam = [list(map(int, input().split())) for _ in range(K)]

grid = [[0] * C for _ in range(R)]
exit = [[0] * C for _ in range(R)]

answer = 0

for i in range(K):
    c, d = gollam[i][0], gollam[i][1]
    current_x = 0
    current_y = c -1

    while True:
        if current_x >= R - 2:
            # 바닥 도달 → 자취 남기고 종료
            for dx_, dy_ in [(0,0), (0,1), (0,-1), (-1,0), (1,0)]:
                nx, ny = current_x + dx_, current_y + dy_
                if 0 <= nx < R and 0 <= ny < C:
                    grid[nx][ny] = i+1

            nx, ny = current_x + dx[d], current_y + dy[d]
            if 0 <= nx < R and 0 <= ny < C:
                exit[nx][ny] = 1

            answer += score(current_x, current_y, grid, exit, R, i, C) + 1
            break

        def is_blocked(x, y):
            return (
                x+2 >= R or y-1 < 0 or y+1 >= C or
                grid[x+1][y-1] != 0 or
                grid[x+1][y+1] != 0 or
                grid[x+2][y] != 0
            )

        if is_blocked(current_x, current_y):
            left_blocked = (
                current_y <= 1 or
                current_y - 2 < 0 or
                current_x + 2 >= R or
                grid[current_x][current_y-2] != 0 or
                (current_x-1 >= 0 and grid[current_x-1][current_y-1] != 0) or
                grid[current_x+1][current_y-1] != 0 or
                grid[current_x+1][current_y-2] != 0 or
                grid[current_x+2][current_y-1] != 0
            )

            right_blocked = (
                current_y >= C - 2 or
                current_y + 2 >= C or
                current_x + 2 >= R or
                grid[current_x][current_y+2] != 0 or
                (current_x-1 >= 0 and grid[current_x-1][current_y+1] != 0) or
                grid[current_x+1][current_y+1] != 0 or
                grid[current_x+1][current_y+2] != 0 or
                grid[current_x+2][current_y+1] != 0
            )

            if not left_blocked:
                current_x += 1
                current_y -= 1
                d = (d - 1) % 4
            elif not right_blocked:
                current_x += 1
                current_y += 1
                d = (d + 1) % 4
            else:
                # 이동 불가능 → 자취 남기고 종료 or 초기화
                for dx_, dy_ in [(0,0), (0,1), (0,-1), (-1,0), (1,0)]:
                    nx, ny = current_x + dx_, current_y + dy_
                    if 0 <= nx < R and 0 <= ny < C:
                        grid[nx][ny] = i+1

                if is_out_of_bounds(current_x, current_y, R, C):
                    grid = [[0] * C for _ in range(R)]
                    exit = [[0] * C for _ in range(R)]
                    break
                else:
                    nx, ny = current_x + dx[d], current_y + dy[d]
                    if 0 <= nx < R and 0 <= ny < C:
                        exit[nx][ny] = 1

                    answer += score(current_x, current_y, grid, exit, R, i, C) + 1
                    break
        else:
            current_x += 1

print(answer)