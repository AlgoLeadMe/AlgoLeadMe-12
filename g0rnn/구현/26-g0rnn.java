import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        Queue<Integer> server = new ArrayDeque<>();
        
        for (int t = 0; t < players.length; t++) {
            while (!server.isEmpty() && t == server.peek()) {
                server.poll();
            }
            //if (players[t] / m <= server.size()) continue;
            
            int numOfNewServer = players[t] / m - server.size();
            for (int i = 0; i < numOfNewServer; i++) {
                server.offer(t + k); // 현재시간 + k만큼을 저장
                answer++;
            }
        }
        return answer;
    }
}