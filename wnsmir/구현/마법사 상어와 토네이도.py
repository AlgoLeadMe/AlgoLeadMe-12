N = int(input())

dx = [0, 1, 0, -1]  # (참고용)
dy = [-1, 0, 1, 0]

# 맵에 상하좌우 2씩 패딩 (전체 크기 = N+4)
sand = []
for _ in range(2):
    sand.append([0]*(N+4))
for _ in range(N):
    row = list(map(int, input().split()))
    row = [0, 0] + row + [0, 0]
    sand.append(row)
for _ in range(2):
    sand.append([0]*(N+4))

# print(sand)  # 디버그용

answer = 0  # 격자 밖으로 나간 모래 누적

# in_range: 원래 격자 영역(패딩 없이 입력된 부분)은 인덱스 2 ~ N+1
def in_range(x, y):
    return 2 <= x <= N+1 and 2 <= y <= N+1

def move_tornado(x, y, d):
    global answer, sand
    # d: 0: 좌, 1: 하, 2: 우, 3: 상
    amount = sand[x][y]
    if amount == 0:
        return
    sand[x][y] = 0
    distributed = 0

    if d == 0:  # 좌 (왼쪽으로 이동했을 때)
        # 1%: (x-1, y+1), (x+1, y+1)
        for (dx_, dy_, per) in [(-1, 1, 1), (1, 1, 1)]:
            val = (amount * per) // 100
            distributed += val
            nx, ny = x + dx_, y + dy_
            if in_range(nx, ny):
                sand[nx][ny] += val
            else:
                answer += val
        # 7%: (x-1, y), (x+1, y)
        for (dx_, dy_, per) in [(-1, 0, 7), (1, 0, 7)]:
            val = (amount * per) // 100
            distributed += val
            nx, ny = x + dx_, y + dy_
            if in_range(nx, ny):
                sand[nx][ny] += val
            else:
                answer += val
        # 2%: (x-2, y), (x+2, y)
        for (dx_, dy_, per) in [(-2, 0, 2), (2, 0, 2)]:
            val = (amount * per) // 100
            distributed += val
            nx, ny = x + dx_, y + dy_
            if in_range(nx, ny):
                sand[nx][ny] += val
            else:
                answer += val
        # 10%: (x-1, y-1), (x+1, y-1)
        for (dx_, dy_, per) in [(-1, -1, 10), (1, -1, 10)]:
            val = (amount * per) // 100
            distributed += val
            nx, ny = x + dx_, y + dy_
            if in_range(nx, ny):
                sand[nx][ny] += val
            else:
                answer += val
        # 5%: (x, y-2)
        val = (amount * 5) // 100
        distributed += val
        nx, ny = x, y - 2
        if in_range(nx, ny):
            sand[nx][ny] += val
        else:
            answer += val
        # 남은 모래 (α): (x, y-1)
        alpha = amount - distributed
        nx, ny = x, y - 1
        if in_range(nx, ny):
            sand[nx][ny] += alpha
        else:
            answer += alpha

    elif d == 1:  # 하 (아래)
        # 1%: (x-1, y-1), (x-1, y+1)
        for (dx_, dy_, per) in [(-1, -1, 1), (-1, 1, 1)]:
            val = (amount * per) // 100
            distributed += val
            nx, ny = x + dx_, y + dy_
            if in_range(nx, ny):
                sand[nx][ny] += val
            else:
                answer += val
        # 7%: (x, y-1), (x, y+1)
        for (dx_, dy_, per) in [(0, -1, 7), (0, 1, 7)]:
            val = (amount * per) // 100
            distributed += val
            nx, ny = x + dx_, y + dy_
            if in_range(nx, ny):
                sand[nx][ny] += val
            else:
                answer += val
        # 2%: (x, y-2), (x, y+2)
        for (dx_, dy_, per) in [(0, -2, 2), (0, 2, 2)]:
            val = (amount * per) // 100
            distributed += val
            nx, ny = x + dx_, y + dy_
            if in_range(nx, ny):
                sand[nx][ny] += val
            else:
                answer += val
        # 10%: (x+1, y-1), (x+1, y+1)
        for (dx_, dy_, per) in [(1, -1, 10), (1, 1, 10)]:
            val = (amount * per) // 100
            distributed += val
            nx, ny = x + dx_, y + dy_
            if in_range(nx, ny):
                sand[nx][ny] += val
            else:
                answer += val
        # 5%: (x+2, y)
        val = (amount * 5) // 100
        distributed += val
        nx, ny = x + 2, y
        if in_range(nx, ny):
            sand[nx][ny] += val
        else:
            answer += val
        # α: (x+1, y)
        alpha = amount - distributed
        nx, ny = x + 1, y
        if in_range(nx, ny):
            sand[nx][ny] += alpha
        else:
            answer += alpha

    elif d == 2:  # 우 (오른쪽)
        # 1%: (x-1, y-1), (x+1, y-1)
        for (dx_, dy_, per) in [(-1, -1, 1), (1, -1, 1)]:
            val = (amount * per) // 100
            distributed += val
            nx, ny = x + dx_, y + dy_
            if in_range(nx, ny):
                sand[nx][ny] += val
            else:
                answer += val
        # 7%: (x-1, y), (x+1, y)
        for (dx_, dy_, per) in [(-1, 0, 7), (1, 0, 7)]:
            val = (amount * per) // 100
            distributed += val
            nx, ny = x + dx_, y + dy_
            if in_range(nx, ny):
                sand[nx][ny] += val
            else:
                answer += val
        # 2%: (x-2, y), (x+2, y)
        for (dx_, dy_, per) in [(-2, 0, 2), (2, 0, 2)]:
            val = (amount * per) // 100
            distributed += val
            nx, ny = x + dx_, y + dy_
            if in_range(nx, ny):
                sand[nx][ny] += val
            else:
                answer += val
        # 10%: (x-1, y+1), (x+1, y+1)
        for (dx_, dy_, per) in [(-1, 1, 10), (1, 1, 10)]:
            val = (amount * per) // 100
            distributed += val
            nx, ny = x + dx_, y + dy_
            if in_range(nx, ny):
                sand[nx][ny] += val
            else:
                answer += val
        # 5%: (x, y+2)
        val = (amount * 5) // 100
        distributed += val
        nx, ny = x, y + 2
        if in_range(nx, ny):
            sand[nx][ny] += val
        else:
            answer += val
        # α: (x, y+1)
        alpha = amount - distributed
        nx, ny = x, y + 1
        if in_range(nx, ny):
            sand[nx][ny] += alpha
        else:
            answer += alpha

    elif d == 3:  # 상 (위)
        # 1%: (x+1, y-1), (x+1, y+1)
        for (dx_, dy_, per) in [(1, -1, 1), (1, 1, 1)]:
            val = (amount * per) // 100
            distributed += val
            nx, ny = x + dx_, y + dy_
            if in_range(nx, ny):
                sand[nx][ny] += val
            else:
                answer += val
        # 7%: (x, y-1), (x, y+1)
        for (dx_, dy_, per) in [(0, -1, 7), (0, 1, 7)]:
            val = (amount * per) // 100
            distributed += val
            nx, ny = x + dx_, y + dy_
            if in_range(nx, ny):
                sand[nx][ny] += val
            else:
                answer += val
        # 2%: (x, y-2), (x, y+2)
        for (dx_, dy_, per) in [(0, -2, 2), (0, 2, 2)]:
            val = (amount * per) // 100
            distributed += val
            nx, ny = x + dx_, y + dy_
            if in_range(nx, ny):
                sand[nx][ny] += val
            else:
                answer += val
        # 10%: (x-1, y-1), (x-1, y+1)
        for (dx_, dy_, per) in [(-1, -1, 10), (-1, 1, 10)]:
            val = (amount * per) // 100
            distributed += val
            nx, ny = x + dx_, y + dy_
            if in_range(nx, ny):
                sand[nx][ny] += val
            else:
                answer += val
        # 5%: (x-2, y)
        val = (amount * 5) // 100
        distributed += val
        nx, ny = x - 2, y
        if in_range(nx, ny):
            sand[nx][ny] += val
        else:
            answer += val
        # α: (x-1, y)
        alpha = amount - distributed
        nx, ny = x - 1, y
        if in_range(nx, ny):
            sand[nx][ny] += alpha
        else:
            answer += alpha

# 토네이도 이동 경로 (스파이럴)
# 시작점은 중앙: (N//2 + 2, N//2 + 2)
current_x = (N // 2) + 2
current_y = (N // 2) + 2

step = 1
direction = 0  # 0: 좌, 1: 하, 2: 우, 3: 상
while True:
    for _ in range(2):
        for _ in range(step):
            if direction == 0:  # 좌
                current_y -= 1
            elif direction == 1:  # 하
                current_x += 1
            elif direction == 2:  # 우
                current_y += 1
            elif direction == 3:  # 상
                current_x -= 1

            move_tornado(current_x, current_y, direction)
            # 실제 격자 영역은 인덱스 2 ~ N+1; 벗어나면 종료
            if current_x < 2 or current_x > N+1 or current_y < 2 or current_y > N+1:
                print(answer)
                exit(0)
        direction = (direction + 1) % 4
    step += 1