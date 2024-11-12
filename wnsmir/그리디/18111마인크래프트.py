def flatten_land(N, M, B, land):
    min_time = float('inf')
    best_height = 0
    
    # 가능한 높이 h를 0에서 256까지 순회
    for h in range(257):
        remove_blocks = 0
        add_blocks = 0
        
        # 각 좌표의 높이에 대해 블록 제거 또는 추가 계산
        for i in range(N):
            for j in range(M):
                height_diff = land[i][j] - h
                if height_diff > 0:
                    remove_blocks += height_diff
                elif height_diff < 0:
                    add_blocks -= height_diff
        
        # 인벤토리 블록과 비교
        if remove_blocks + B >= add_blocks:
            time = remove_blocks * 2 + add_blocks
            # 최소 시간 갱신 및 최대 높이 선택
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