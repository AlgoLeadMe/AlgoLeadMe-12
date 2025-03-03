N = int(input())
orders = input().split()
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]
directions = ["U", "D", "L", "R"]

x, y = 1, 1

for order in orders:
    for i in range(4):
        if order == directions[i]:
            nx = x + dx[i]
            ny = y + dy[i]
    
    if nx < 1 or ny < 1 or nx > N or ny > N:
        continue
    else:
        x = nx
        y = ny

print(x, y)