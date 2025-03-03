import sys
input = sys.stdin.readline

n = int(input())
k = int(input())

dx = [0, 1, 0, -1]  # 우, 하, 좌, 상 (시계방향)
dy = [1, 0, -1, 0]

# 내장함수 map과 충돌하지 않도록 변수명을 board로 변경
board = [[0] * (n + 1) for _ in range(n + 1)]

# 사과 배치
for _ in range(k):
    a, b = map(int, input().split())
    board[a][b] = 1

l = int(input())
change_dir = []

for _ in range(l):
    t, d = input().split()
    change_dir.append((int(t), d))

# 초기 방향: 오른쪽(0: 우, 1: 하, 2: 좌, 3: 상)
direction = 0

def turn(direction, c):
    if c == "L":
        return (direction - 1) % 4
    else:  # "D" 등 오른쪽 회전인 경우
        return (direction + 1) % 4

# 뱀의 초기 위치
x, y = 1, 1
board[x][y] = 2
time = 0
index = 0
q = [(x, y)]  # 뱀의 몸의 위치를 저장 (머리부터 꼬리)

while True:
    nx = x + dx[direction]
    ny = y + dy[direction]

    # 벽에 부딪히거나 자기 몸에 부딪히면 종료
    if not (1 <= nx <= n and 1 <= ny <= n) or board[nx][ny] == 2:
        time += 1
        break

    # 이동하는 칸에 사과가 없다면 (빈 칸)
    if board[nx][ny] == 0:
        board[nx][ny] = 2
        q.append((nx, ny))
        # 꼬리 이동: 뱀의 몸 길이를 유지하기 위해 꼬리 제거
        px, py = q.pop(0)
        board[px][py] = 0
    # 사과가 있으면 (뱀 길이 증가)
    elif board[nx][ny] == 1:
        board[nx][ny] = 2
        q.append((nx, ny))
    
    # 머리 위치 갱신 및 시간 증가
    x, y = nx, ny
    time += 1

    # 방향 전환 처리
    if index < len(change_dir) and time == change_dir[index][0]:
        direction = turn(direction, change_dir[index][1])
        index += 1

print(time)