N, M, K = map(int, input().split())

data = list(map(int, input().split()))

data.sort()

first = data[-1]
second = data[-2]

result = 0

while True:
    for i in range(K):
        if M == 0:
            break
        result += first

        M -= 1
    
    if M == 0:
        break
    result += second
    M -= 1

print(result)