import heapq

INF = int(1e9)

n = int(input())
m = int(input())

graph = [[] for _ in range(n + 1)]

#모든 간선 정보 입력받기
for _ in range(m):
    start, end, cost = map(int, input().split())
    graph[start].append((end, cost))

#시작점, 도착점 입력받기 (m+3번째 줄)
start_city, end_city = map(int, input().split())

# 최단 거리 테이블을 무한대로 초기화
distance = [INF] * (n + 1)

# 다익스트라 정의
def dijkstra(start):
    # 우선순위 큐(Heap) 사용
    q = []
    # 시작 지점 비용 0으로 초기화하고 큐에 삽입
    heapq.heappush(q, (0, start))
    distance[start] = 0
    
    while q:
        # 가장 비용이 적은 노드 꺼내기
        dist, now = heapq.heappop(q)
        
        # 이미 처리된 노드라면 무시
        if distance[now] < dist:
            continue
        
        # 현재 노드와 연결된 다른 노드들 확인
        for next_city, next_cost in graph[now]:
            cost = dist + next_cost
            # 현재 노드를 거쳐 다른 노드로 가는 비용이 더 적다면 갱신
            if cost < distance[next_city]:
                distance[next_city] = cost
                heapq.heappush(q, (cost, next_city))

dijkstra(start_city)
print(distance[end_city])

