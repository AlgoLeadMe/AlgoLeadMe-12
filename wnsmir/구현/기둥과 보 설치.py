def possible(answer):
    for x, y, stuff in answer:
        if stuff == 0:
            if y == 0 or [x-1, y, 1] in answer or [x, y, 1] in answer or [x, y-1, 0] in answer:
                continue
            return False
        elif stuff == 1:
            if [x, y - 1, 0] in answer or [x + 1, y - 1, 0] in answer or ([x - 1, y, 1] in answer and [x + 1, y, 1] in answer):
                continue
            return False
    return True

def solution(n, build_frame):
    answer = []
    for frame in build_frame:
        x, y, stuff, operate = frame

        if operate == 0: # 삭제하는 경우
            answer.remove([x, y, stuff]) # 일단 삭제를 해본 뒤에 
            if not possible(answer): # 가능한 구조물인지 확인
                answer.append([x, y, stuff]) # 가능한 구조물이 아니라면 다시 설치

        if operate == 1: # 설치하는 경우
            answer.append([x, y, stuff]) # 일단 설치를 해본 뒤에 
            if not possible(answer): # 가능한 구조물인지 확인
                answer.remove([x, y,stuff]) #가능한 구조물이 아니라면 다시 제거

    return sorted(answer)