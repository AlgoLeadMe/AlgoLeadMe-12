def max_lan_length(K, N, lengths):
    start, end = 1, max(lengths)  # 최소 1, 최대 길이 설정
    result = 0

    while start <= end:
        mid = (start + end) // 2  # 중간값 설정
        count = sum(l // mid for l in lengths)  # mid 길이로 잘라서 만들 수 있는 랜선 개수 계산

        if count >= N:  # N개 이상 만들 수 있으면
            result = mid  # 현재 길이를 저장
            start = mid + 1  # 더 긴 길이를 시도
        else:
            end = mid - 1  # 더 짧은 길이를 시도

    return result

# 입력 처리
K, N = map(int, input().split())
lengths = [int(input()) for _ in range(K)]

# 결과 출력
print(max_lan_length(K, N, lengths))