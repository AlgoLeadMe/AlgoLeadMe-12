package org.example;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static int N, M;
    static int[][] city;
    static ArrayList<int[]> houses = new ArrayList<> ();
    static ArrayList<int[]> stores = new ArrayList<> ();
    static int min_distance = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        city = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                city[i][j] = sc.nextInt();
                if (city[i][j] == 1) {
                    houses.add(new int[] {i, j});
                }
                if (city[i][j] == 2) {
                    stores.add(new int[] {i, j});
                }

            }
        }

        choiceStore(new int[M], 0, 0);
        System.out.println(min_distance);
    }

    static void choiceStore(int[] selected, int start, int count) {
        if (count == M) {
            min_distance = Math.min(min_distance, chickenDistance(selected));
            return;
        }
        for (int i = start; i < stores.size(); i++) {
            selected[count] = i;
            choiceStore(selected, i + 1, count + 1);
        }
    }

    static int chickenDistance(int[] selected) {
        int total_chicken_distance = 0;     //각각의 집에서 치킨집까지 거리의 합
        for (int[] house : houses) {
            int hx = house[0], hy = house[1];   //houseX, houseY
            int min_chicken_distance = Integer.MAX_VALUE;
            for (int index : selected) {
                int[] store = stores.get(index);
                int sx = store[0], sy = store[1];   //storeX, storeY
                int distance = Math.abs(hx-sx) + Math.abs(hy-sy);
                min_chicken_distance = Math.min(min_chicken_distance, distance);
            }
            total_chicken_distance += min_chicken_distance;

        }
        return total_chicken_distance;
    }


}