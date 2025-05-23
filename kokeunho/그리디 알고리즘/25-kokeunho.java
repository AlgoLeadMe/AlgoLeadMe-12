import java.util.Scanner;

public class Main {
    static String L, R;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        L = sc.next();
        R = sc.next();

        if (L.length() != R.length()) {
            System.out.print(0);
            return;
        }

        int count = 0;
        for (int i = 0; i < L.length(); i++) {
            if (L.charAt(i) != R.charAt(i)) break;

            if (L.charAt(i) == '8') count++;
        }

        System.out.print(count);
    }
}
