N = int(input())
matrix = []

for i in range(N):
    row = list(map(int, input().split()))
    matrix.append(row)

#이전 줄
prev_dp_max = matrix[0][:]
prev_dp_min = matrix[0][:]

# 두번째 줄부터
for i in range(1, N):
    curr_dp_max = [0] * 3  #현재줄의 최대값
    curr_dp_min = [0] * 3  #현재줄의 최소값
    
    # 첫번째 열 (j == 0)
    curr_dp_max[0] = matrix[i][0] + max(prev_dp_max[0], prev_dp_max[1])
    curr_dp_min[0] = matrix[i][0] + min(prev_dp_min[0], prev_dp_min[1])
    
    # 두번째 열 (j == 1)
    curr_dp_max[1] = matrix[i][1] + max(prev_dp_max[0], prev_dp_max[1], prev_dp_max[2])
    curr_dp_min[1] = matrix[i][1] + min(prev_dp_min[0], prev_dp_min[1], prev_dp_min[2])
    
    # 세번째 열 (j == 2)
    curr_dp_max[2] = matrix[i][2] + max(prev_dp_max[1], prev_dp_max[2])
    curr_dp_min[2] = matrix[i][2] + min(prev_dp_min[1], prev_dp_min[2])

    # 현재줄을 이전 줄로 업데이트 => 메모리절약을 위해
    prev_dp_max = curr_dp_max[:]
    prev_dp_min = curr_dp_min[:]

# 마지막 줄의 최대값과 최소값 출력
print(max(prev_dp_max), min(prev_dp_min))