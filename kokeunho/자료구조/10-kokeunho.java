package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int n = sc.nextInt();

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();

            if (maxHeap.size() <= minHeap.size()) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }

            if (!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                int little = minHeap.poll();
                int big = maxHeap.poll();

                maxHeap.offer(little);
                minHeap.offer(big);
            }
            sb.append(maxHeap.peek()).append("\n");
        }
        System.out.println(sb);
    }
}