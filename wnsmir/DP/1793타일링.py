def calculate_tiling_ways():
    import sys
    input = sys.stdin.read
    data = input().split()
    
    max_n = 250
    dp = [0] * (max_n + 1)  # DP 배열 초기화, 0부터 250까지 저장할 공간 생성
    dp[0], dp[1] = 1, 1  # 기저 조건: dp[0] = 1, dp[1] = 1
    
    # DP 점화식
    for i in range(2, max_n + 1):
        dp[i] = dp[i-1] + dp[i-2]
    
    results = []  # 결과 저장 리스트
    for n in map(int, data):  # 입력된 각 테스트 케이스에 대해
        results.append(dp[n])  # dp[n] 값을 결과 리스트에 추가
    
    # 결과를 출력: 각 값을 줄바꿈으로 구분하여 출력
    sys.stdout.write("\n".join(map(str, results)) + "\n")

if __name__ == "__main__":
    calculate_tiling_ways()  # 메인 함수 실행