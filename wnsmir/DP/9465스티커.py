import sys
input = sys.stdin.read

def max_sticker_score(n, stickers):
    # n이 1일 때는 간단히 큰 값을 반환
    if n == 1:
        return max(stickers[0][0], stickers[1][0])
    
    # DP 배열 초기화
    dp = [[0] * n for _ in range(2)]
    
    # 초기값 설정
    dp[0][0] = stickers[0][0]
    dp[1][0] = stickers[1][0]
    
    # n이 2 이상인 경우 첫 두 열을 초기화
    if n > 1:
        dp[0][1] = dp[1][0] + stickers[0][1]
        dp[1][1] = dp[0][0] + stickers[1][1]
    
    # DP 배열 채우기
    for i in range(2, n):
        dp[0][i] = max(dp[1][i-1], dp[1][i-2]) + stickers[0][i] # i-2까지 처리 (점화식)
        dp[1][i] = max(dp[0][i-1], dp[0][i-2]) + stickers[1][i]
    
    # 최댓값 반환
    return max(dp[0][n-1], dp[1][n-1])

# 입력 처리
data = input().strip().split()
T = int(data[0])  # 테스트 케이스 수
index = 1
results = []

for _ in range(T):
    n = int(data[index])
    stickers = [
        list(map(int, data[index + 1:index + 1 + n])),
        list(map(int, data[index + 1 + n:index + 1 + 2 * n]))
    ]
    results.append(max_sticker_score(n, stickers))
    index += 2 * n + 1

# 결과 출력
for result in results:
    print(result)