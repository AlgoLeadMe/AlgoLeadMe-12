package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] visited = new int[100001];
        Arrays.fill(visited, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        visited[n] = 0;

        while(!queue.isEmpty()) {
            int current = queue.poll();

            if(current == k) {
                System.out.println(visited[current]);
                break;
            }

            for(int next : new int[]{current - 1, current + 1, current * 2}) {
                if(next >= 0 && next <= 100000 && visited[next] == -1) {
                    visited[next] = visited[current] + 1;
                    queue.add(next);
                }
            }

        }
    }
}