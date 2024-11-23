import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**5)

n = int(input())

tree = [[] for _ in range(n+1)]
parent = [[0] * 20 for _ in range(n+1)]
depth = [0] * (n+1) 
visited = [False] * (n+1)

for _ in range(n-1):
    p, c = map(int, input().split())
    tree[p].append(c)         
    tree[c].append(p)

def dfs(node, d):
    visited[node] = True
    depth[node] = d
    for i in tree[node]:
        if not visited[i]:
            parent[i][0] = node 
            dfs(i, d + 1)

def sparse_table():
    for j in range(1, 20):
        for i in range(1, n+1):
            if parent[i][j-1] != 0:
                parent[i][j] = parent[parent[i][j-1]][j-1]

def lca(a, b):
    if depth[a] < depth[b]:
        a, b = b, a
    
    for i in range(19, -1, -1):
        if depth[a] - depth[b] >= (1 << i):
            a = parent[a][i]
    
    if a == b:
        return a
    
    for i in range(19, -1, -1):
        if parent[a][i] != parent[b][i]:
            a = parent[a][i]
            b = parent[b][i]
    
    return parent[a][0] 

dfs(1, 0)
sparse_table()

m = int(input())

for _ in range(m):
    a, b = map(int, input().split())
    print(lca(a, b))

    