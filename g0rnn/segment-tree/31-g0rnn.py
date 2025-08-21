import sys
input = sys.stdin.readline

def minimize(x):
    if x > 0:
        return 1
    elif x < 0:
        return -1
    else:
        return 0

def build(node, s, e):
    if s == e:
        tree[node] = minimize(nums[s])
        return
    
    mid = (s + e) // 2
    build(node * 2, s, mid)
    build(node * 2 + 1, mid + 1, e)
    tree[node] = tree[node * 2] * tree[node * 2 + 1]

def update(node, s, e, i, v):
    if s == e:
        tree[node] = minimize(v)
        return
    
    mid = (s + e) // 2
    if mid < i:
        update(node*2 + 1, mid + 1, e, i, v)
    else:
        update(node*2, s, mid, i, v)

    tree[node] = tree[node * 2] * tree[node * 2 + 1]

def query(node, s, e, i, j):
    if j < s or e < i:
        return 1
    if i <= s and e <= j:
        return tree[node]
    
    mid = (s + e) // 2
    left = query(node*2,s, mid, i, j)
    right = query(node*2 + 1, mid + 1, e, i, j)
    return left * right

while True:
    try:
        n, k = map(int, input().split())
        nums = list(map(int, input().split()))
        tree = [0] * (4 * n)

        build(1, 0, n - 1)

        output = []
        for _ in range(k):
            cmd, i, v = input().split()
            i = int(i)
            v = int(v)
            if cmd == 'C':
                update(1, 0, n-1, i - 1, v)
            elif cmd == 'P':
                result = query(1, 0, n-1, i - 1, v - 1)
                if result > 0:
                    output.append('+')
                elif result < 0:
                    output.append('-')
                else:
                    output.append('0')
        print(''.join(output))
    except:
        break
