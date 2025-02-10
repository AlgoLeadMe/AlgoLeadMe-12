import java.util.*;

public class Sol214288P {
    class Solution {
        private List<List<Integer>> mentors = new ArrayList<>();

        //n : 멘토 수, k : 유형 수
        public int solution(int k, int n, int[][] reqs) {
            int answer = Integer.MAX_VALUE;
            Integer[] area = new Integer[k + 1];
            Arrays.fill(area, 1);
            backtracking(n - k, 1, new ArrayList<Integer>(Arrays.asList(area)));

            for (List<Integer> mentor : mentors) {
                answer = Math.min(answer, wait(k, mentor, reqs));
            }

            return answer;
        }

        private void backtracking(int remain, int idx, List<Integer> area) {
            if (remain <= 0) {
                mentors.add(area);
                return;
            }
            for (int i = idx; i < area.size(); i++) {
                area.set(i, area.get(i) + 1);
                backtracking(remain - 1, i, new ArrayList<>(area));
                area.set(i, area.get(i) - 1);
            }
        }

        private int wait(int k, List<Integer> mentor, int[][] reqs) {
            PriorityQueue<Integer>[] pq = new PriorityQueue[k + 1];
            int waitTime = 0;

            for (int i = 1; i <= k; i++) {
                pq[i] = new PriorityQueue<Integer>();
                for (int j = 0; j < mentor.get(i); j++) pq[i].add(0);
            }

            for (int[] req : reqs) {
                int start = req[0];
                int during = req[1];
                int type = req[2];
                int time = pq[type].poll();

                if (time > start) {
                    waitTime += time - start;
                    pq[type].add(time + during);
                } else if (time < start) {
                    pq[type].add(start + during);
                } else {
                    pq[type].add(time + during);
                }
            }
            return waitTime;
        }
    }
}
