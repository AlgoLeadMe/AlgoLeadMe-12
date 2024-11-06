import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        String expression;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        expression = br.readLine();

        int sum = Integer.MAX_VALUE;
        String[] splitNumbersByMinus = expression.split("-");

        for (int i = 0; i < splitNumbersByMinus.length; i++) {
            String[] parts = splitNumbersByMinus[i].split("\\+");
            int partSum = 0;

            for (int j = 0; j < parts.length; j++)
                partSum += Integer.parseInt(parts[j]);

            if(sum == Integer.MAX_VALUE) sum = partSum;
            else sum -= partSum;
        }

        System.out.println(sum);
    }
}