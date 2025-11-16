N = int(input())
grid = [[0]*101 for _ in range(101)]

def rot90(p, pivot):
    x, y = p
    px, py = pivot
    return (px - (y - py), py + (x - px))

def curve(last_gen, last_spot):
    new_gen = last_gen[:]
    for dot in reversed(last_gen[:-1]): # 중심 제외 역순으로
        nx, ny = rot90(dot, last_spot)
        if 0 <= nx <= 100 and 0 <= ny <= 100:
            grid[ny][nx] = 1
        new_gen.append((nx, ny))
    return new_gen, new_gen[-1]

for _ in range(N):
    x, y, d, g = map(int, input().split())

    last_gen = [(x, y)]
    grid[y][x] = 1

    # 0세대 만들기
    if d == 0:
        last_spot = (x+1, y)
    elif d == 1:
        last_spot = (x, y-1)
    elif d == 2:
        last_spot = (x-1, y)
    else:
        last_spot = (x, y+1)

    last_gen.append(last_spot)
    lx, ly = last_spot
    if 0 <= lx <= 100 and 0 <= ly <= 100:
        grid[ly][lx] = 1

    # g 세대수만큼
    for _ in range(g):
        last_gen, last_spot = curve(last_gen, last_spot)

# 사각형 개수
answer = 0
for y in range(100):
    for x in range(100):
        if grid[y][x] == 1 and grid[y][x+1] == 1 and grid[y+1][x] == 1 and grid[y+1][x+1] == 1:
            answer += 1

print(answer)