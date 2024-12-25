package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        String num = sc.next();

        Stack<Character> stack = new Stack<>();

        int count = 0;

        for(int i = 0; i < n; i++) {
            char c = num.charAt(i);
            while(!stack.isEmpty() && count < k && stack.peek() < c) {
                stack.pop();
                count++;
            }
            stack.push(c);
        }

        StringBuilder result = new StringBuilder();
        for(int i = 0; i < n - k; i++) {
            result.append(stack.get(i));
        }
        System.out.println(result.toString());

    }
}