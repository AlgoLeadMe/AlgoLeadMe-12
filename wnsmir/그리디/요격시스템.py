def solution(targets):
    # 끝나는 지점 기준 오름차순 정렬
    targets.sort(key=lambda x: x[1])

    count = 0
    last_shot = -1
    for start, end in targets:
        if start >= last_shot:
            count += 1
            last_shot = end
            
    return count