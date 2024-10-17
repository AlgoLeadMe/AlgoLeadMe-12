from collections import deque

def kahn_topological_sort(vertices, edges):
    # 각 노드의 들어오는 간선 수를 저장할 딕셔너리 초기화
    in_degree = {i: 0 for i in range(vertices)}
    # 인접 리스트를 저장할 딕셔너리 초기화
    adj_list = {i: [] for i in range(vertices)}

    # 간선 리스트를 순회하며 in-degree와 adj_list를 업데이트
    for src, dest in edges:
        adj_list[src].append(dest)
        in_degree[dest] += 1

    # 들어오는 간선의 수가 0인 노드들을 큐에 저장
    zero_in_degree_queue = deque([k for k in in_degree if in_degree[k] == 0])
    
    topological_order = []

    # 위상 정렬 수행
    while zero_in_degree_queue:
        vertex = zero_in_degree_queue.popleft()
        topological_order.append(vertex)

        # 인접 노드의 in-degree 감소 및 in-degree가 0인 경우 큐에 추가
        for neighbor in adj_list[vertex]:
            in_degree[neighbor] -= 1
            if in_degree[neighbor] == 0:
                zero_in_degree_queue.append(neighbor)

    # 모든 노드를 방문했는지 확인
    if len(topological_order) == vertices:
        return topological_order
    else:
        return []  # 사이클이 존재하는 경우 빈 리스트 반환

# 예제 사용법
vertices = 6
edges = [(5, 2), (5, 0), (4, 0), (4, 1), (2, 3), (3, 1)]
print(kahn_topological_sort(vertices, edges))