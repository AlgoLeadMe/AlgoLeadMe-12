import sys
input = sys.stdin.readline

def init(node, start, end):
    if start == end:
        tree[node] = nums[start]
    else:
        mid = (start + end) // 2
        init(node * 2, start, mid)
        init(node * 2 + 1, mid + 1, end)
        tree[node] = tree[node * 2] + tree[node * 2 + 1]

def update(node, start, end, idx, val):
    if start == end:
        nums[idx] = val
        tree[node] = val
    else:
        mid = (start + end) // 2
        if start <= idx <= mid:
            update(node * 2, start, mid, idx, val)
        else:
            update(node * 2 + 1, mid + 1, end, idx, val)
        tree[node] = tree[node * 2] + tree[node * 2 + 1]

def query(node, start, end, l, r):
    if r < start or end < l:
        return 0
    if l <= start and end <= r:
        return tree[node]
    mid = (start + end) // 2
    return query(node * 2, start, mid, l, r) + query(node * 2 + 1, mid + 1, end, l, r)

# 입력
n, q = map(int, input().split())
nums = [0] + list(map(int, input().split())) ## int는 가변 정수라 long 보다도 커질 수 있음.. 아니 걍 크기제한이 없음
tree = [0] * (4 * n)

init(1, 1, n)
for _ in range(q):
    x, y, a, b = map(int, input().split())
    l = min(x, y)
    r = max(x, y)
    
    print(query(1, 1, n, l, r))
    update(1, 1, n, a, b)