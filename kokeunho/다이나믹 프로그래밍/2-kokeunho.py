N, K = map(int, input().split())
items = []

for _ in range(N):
    W, V = map(int, input().split())
    items.append((W, V))
    
dp = [[0] * (K+1) for _ in range(N+1)]

for i in range(1, N+1):
    weight, value = items[i-1]
    for j in range(1, K+1):
        if j >= weight:
            dp[i][j] = max(dp[i-1][j], dp[i-1][j-weight] + value)
        else: 
            dp[i][j] = dp[i-1][j]
            
print(dp[N][K])
            




