def solution(cap, n, deliveries, pickups):
    answer = 0
    last_del = n-1
    last_pick = n-1
    
    # 아직 배달, 수거할 집이 남아있다면 반복
    while last_del >= 0 or last_pick >= 0:
        # 가장 먼 배달/수거할 집 업데이트
        while last_del >= 0 and deliveries[last_del] == 0:
            last_del -= 1
        while last_pick >= 0 and pickups[last_pick] == 0:
            last_pick -= 1
        
        # 이번턴에 갈 가장 마지막집
        far = max(last_pick, last_del) + 1
        answer += far * 2

        # deliver == 한번에 가능한 배달 수
        deliver = cap
        
        # 배달도 가능하고, 배달해야할 집이 남았디면
        while deliver > 0 and last_del >= 0:
            # 집이 비었을떄 다음집으로
            if deliveries[last_del] == 0:
                last_del -= 1
                continue
            
            # 최대용량보다 배달할게 적을때 최대용량 줄이고 다음집
            if deliveries[last_del] <= deliver:
                deliver -= deliveries[last_del]
                deliveries[last_del] = 0
                last_del -= 1
            
            # 최대용량을 넘어설때 최대용량만큼 배달하고 종료
            else:
                deliveries[last_del] -= deliver
                deliver = 0

        pick = cap
        while pick > 0 and last_pick >= 0:
            if pickups[last_pick] == 0:
                last_pick -= 1
                continue
                
            if pickups[last_pick] <= pick:
                pick -= pickups[last_pick]
                pickups[last_pick] = 0
                last_pick -= 1
                
            else:
                pickups[last_pick] -= pick
                pick = 0

    return answer