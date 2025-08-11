from collections import defaultdict
from itertools import combinations
from bisect import bisect_left

def solution(info, query):
    # 전처리: 16개 키에 점수 적재
    db = defaultdict(list)
    for s in info:
        lang, job, career, food, score = s.split()
        score = int(score)
        fields = [lang, job, career, food]

        # 4개의 필드에서 0~4개를 '-'로 치환한 모든 조합(2^4=16)
        for r in range(5):
            for idxs in combinations(range(4), r):
                key = fields[:]  # 복사
                for i in idxs:
                    key[i] = '-'
                db[tuple(key)].append(score)

    # 각 키의 점수 리스트 정렬(이분 탐색)
    for k in db:
        db[k].sort()

    # 쿼리 처리: 키 만들고, lower_bound로 개수 계산
    answer = []
    for que in query:
        # "java and backend and junior and pizza 100" -> ["java","backend","junior","pizza","100"]
        que = que.replace(" and ", " ")
        a, b, c, d, min_score = que.split()
        key = (a, b, c, d)
        scores = db.get(key, [])
        # 점수 >= min_score 인 원소 개수 = 전체 - 첫 위치
        idx = bisect_left(scores, int(min_score))
        answer.append(len(scores) - idx)

    return answer