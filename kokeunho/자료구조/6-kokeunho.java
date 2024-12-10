
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        scanner.close();

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }

        System.out.print("<");
        while(!queue.isEmpty()) {
            for(int i = 1; i < k; i++) {
                queue.add(queue.poll());
            }
            System.out.print(queue.poll());
            if (!queue.isEmpty()) {
                System.out.print(", ");
            }
        }
        System.out.println(">");
    }
}