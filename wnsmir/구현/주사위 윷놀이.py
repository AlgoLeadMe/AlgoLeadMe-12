def product(pool, repeat):
    result = []
    r = repeat
    choice = [None] * r
    n = len(pool)

    def dfs(depth):
        if depth == r:
            result.append(tuple(choice))
            return
        for i in range(n):
            choice[depth] = pool[i]
            dfs(depth + 1)

    dfs(0)
    return result

grid = [2 * i for i in range(1, 21)] + [0]          # 메인 스트리트(0‒20, 20=FINISH)
blue1 = [13, 16, 19]                                # 10 → 25
blue2 = [22, 24]                                    # 20 → 25
blue3 = [28, 27, 26]                                # 30 → 25
five  = [25, 30, 35]                                # 25 → 40

# 입력
dices = list(map(int, input().split()))

horses = [0, 1, 2, 3]                               
cases = product(horses, 10)

answer = 0

for case in cases:
    horse_place = [-1] * 4                          # 각 말의 위치(시작 전 -1)
    point = 0
    valid = True

    for turn in range(10):
        horse = case[turn]                         
        dice  = dices[turn]                    

        # 이미 골인한 말을 또 고르면 무효
        if horse_place[horse] == 20:
            valid = False
            break

        pos = horse_place[horse]                    

        # 도착지점이 blue1일떄
        if pos == 4:
            if dice < 4:                            # blue1 안으로
                nxt = 100 + (dice - 1)              # 0,1,2 → 100,101,102
                if nxt in horse_place:
                    valid = False; break
                horse_place[horse] = nxt
                point += blue1[dice - 1]
                continue
            else:                                   # 바로 FIVE 또는 메인
                pos = 100 + 2                       # blue1의 마지막 칸으로 맞춰 놓고 이후 처리
                dice -= 3 

        # blue1 내부(100‒102)일때
        if 100 <= pos <= 102:
            cur = pos - 100                         # 내부 인덱스 0‒2
            nxt = cur + dice
            if nxt <= 2:                            # 여전히 blue1
                nxt_pos = 100 + nxt
                if nxt_pos in horse_place:
                    valid = False; break
                horse_place[horse] = nxt_pos
                point += blue1[nxt]
                continue
            else:                                   # FIVE, 40 또는 FINISH
                cur = nxt - 3                       # FIVE 구간 0‒2‒3 (0,1,2는 five 인덱스)
                if cur < 3:
                    nxt_pos = 400 + cur             # FIVE 인코딩 400+0,1,2
                    if nxt_pos in horse_place:
                        valid = False; break
                    horse_place[horse] = nxt_pos
                    point += five[cur]
                    continue
                elif cur == 3:                      # 40
                    if 19 in horse_place:           # 메인 스트리트 19 = 40
                        valid = False; break
                    horse_place[horse] = 19
                    point += 40
                    continue
                else:                               # 넘어서면 골인
                    horse_place[horse] = 20
                    continue

        # 도착지점이 blue2일때
        if pos == 9:
            if dice < 3:
                nxt = 200 + (dice - 1)              # 0,1 → 200,201
                if nxt in horse_place:
                    valid = False; break
                horse_place[horse] = nxt
                point += blue2[dice - 1]
                continue
            else:
                pos = 200 + 1                       # blue2 마지막 칸으로 맞춰 놓음
                dice -= 2 

        # 도착지점이 blue2 내부(200‒201)일떄
        if 200 <= pos <= 201:
            cur = pos - 200
            nxt = cur + dice
            if nxt <= 1:                            # 아직 blue2
                nxt_pos = 200 + nxt
                if nxt_pos in horse_place:
                    valid = False; break
                horse_place[horse] = nxt_pos
                point += blue2[nxt]
                continue
            else:                                   # FIVE, 40, FINISH
                cur = nxt - 2
                if cur < 3:
                    nxt_pos = 400 + cur
                    if nxt_pos in horse_place:
                        valid = False; break
                    horse_place[horse] = nxt_pos
                    point += five[cur]
                    continue
                elif cur == 3:
                    if 19 in horse_place:
                        valid = False; break
                    horse_place[horse] = 19
                    point += 40
                    continue
                else:
                    horse_place[horse] = 20
                    continue

        # 도착지점이 blue3일때
        if pos == 14:
            if dice < 4:
                nxt = 300 + (dice - 1)              # 0,1,2 → 300,301,302
                if nxt in horse_place:
                    valid = False; break
                horse_place[horse] = nxt
                point += blue3[dice - 1]
                continue
            else:
                pos = 300 + 2                       # blue3 마지막 칸
                dice -= 3 

        # 도착지점이 blue3 내부(300‒302)일때
        if 300 <= pos <= 302:
            cur = pos - 300
            nxt = cur + dice
            if nxt <= 2:
                nxt_pos = 300 + nxt
                if nxt_pos in horse_place:
                    valid = False; break
                horse_place[horse] = nxt_pos
                point += blue3[nxt]
                continue
            else:
                cur = nxt - 3
                if cur < 3:
                    nxt_pos = 400 + cur
                    if nxt_pos in horse_place:
                        valid = False; break
                    horse_place[horse] = nxt_pos
                    point += five[cur]
                    continue
                elif cur == 3:
                    if 19 in horse_place:
                        valid = False; break
                    horse_place[horse] = 19
                    point += 40
                    continue
                else:
                    horse_place[horse] = 20
                    continue

        # 도착지점이 FIVE(25-30-35) 내부(400‒402)일때
        if 400 <= pos <= 402:
            cur = pos - 400            # 0,1,2  ⇒ 25,30,35
            nxt = cur + dice

            if nxt <= 2:               # 25→30 또는 30→35
                nxt_pos = 400 + nxt
                if nxt_pos in horse_place:
                    valid = False; break
                horse_place[horse] = nxt_pos
                point += five[nxt]      # five[0]=25, five[1]=30, five[2]=35
                continue

            elif nxt == 3:             # 35→40
                if 19 in horse_place:   # 메인 스트리트의 40
                    valid = False; break
                horse_place[horse] = 19
                point += 40
                continue

            else:                      # 40 넘으면 골인
                horse_place[horse] = 20
                continue

        # 메인 스트리트일때
        nxt_pos = pos + dice
        if nxt_pos > 20:
            horse_place[horse] = 20                 # 초과하면 골인
            continue
        if nxt_pos in horse_place:                  # 겹침 금지
            valid = False
            break
        horse_place[horse] = nxt_pos
        point += grid[nxt_pos]

    if valid and point > answer:
        answer = point

print(answer)