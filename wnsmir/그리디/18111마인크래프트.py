from collections import Counter

def flatten_land(N, M, B, land):
    # 땅 높이 빈도 계산
    height_counts = Counter()
    for row in land:
        height_counts.update(row)
    
    # 가장 많이 등장한 높이 찾기
    common_height = height_counts.most_common(1)[0][0]
    
    # 최대 높이와 최소 높이 계산 (최대 256까지 제한)
    min_height = max(0, common_height - 10)  # 주변 범위로 최소 높이 제한
    max_height = min(256, common_height + 10)  # 주변 범위로 최대 높이 제한
    
    min_time = float('inf')
    best_height = 0
    
    # common_height 주변의 제한된 범위에서 탐색
    for h in range(min_height, max_height + 1):
        remove_blocks = 0
        add_blocks = 0
        
        # 각 좌표에 대해 블록 계산
        for i in range(N):
            for j in range(M):
                height_diff = land[i][j] - h
                if height_diff > 0:
                    remove_blocks += height_diff
                elif height_diff < 0:
                    add_blocks -= height_diff
        
        # 인벤토리와 비교하여 작업 가능 여부 판단
        if remove_blocks + B >= add_blocks:
            time = remove_blocks * 2 + add_blocks
            if time < min_time or (time == min_time and h > best_height):
                min_time = time
                best_height = h
    
    return min_time, best_height

# 입력 처리
N, M, B = map(int, input().split())
land = [list(map(int, input().split())) for _ in range(N)]

# 결과 계산 및 출력
time, height = flatten_land(N, M, B, land)
print(time, height)