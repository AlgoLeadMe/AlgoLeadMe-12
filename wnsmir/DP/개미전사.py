N = int(input())

Food = list(map(int, input().split()))

dp = [0]*(N+1)
dp[1] = Food[0]
dp[2] = max(Food[0], Food[1])

for i in range(4, N+1):
    dp[i] = max(dp[i-2]+Food[i-1], dp[i-1])

print(dp[N])