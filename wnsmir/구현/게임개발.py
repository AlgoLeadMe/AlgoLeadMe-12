n, m = map(int, input().split())
x, y, d = map(int, input().split())
visited_map = [[0] * m for _ in range(n)]
visited_map[x][y] = 1

array = []
for _ in range(n):
    array.append(list(map(int, input().split())))

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

def turn_left():
    global d
    d -= 1
    if d == -1:
        d = 3

count = 1
turn_time = 0

while True:
    turn_left()
    nx = x + dx[d]
    ny = y + dy[d]

    if visited_map[nx][ny] == 0 and array[nx][ny] == 0:
        visited_map[nx][ny] = 1
        count += 1
        x, y = nx, ny
        turn_time = 0
    else:
        turn_time += 1

    if turn_time == 4:
        nx = x - dx[d]
        ny = y - dy[d]
        if array[nx][ny] == 0:
            x, y = nx, ny
        else:
            break
        turn_time = 0

print(count)