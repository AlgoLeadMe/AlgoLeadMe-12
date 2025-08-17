import java.util.*;

class Solution {
    public String[] solution(String[] expressions) {
        //최소 가능 진법 찾고 X 포함된 식 따로 분리하기
        List<String> exprIncludeX = new ArrayList<>();
        int minBase = 2;
        for (String expr : expressions) {
            String[] parts = expr.split(" ");
            minBase = Math.max(minBase, calculateMinBase(parts[0], parts[2], parts[4]));
            if (parts[4].equals("X")) exprIncludeX.add(expr);
        }
        //가능한 진법들 중 식에 대입하여 검증되는 진법만 추리기
        List<Integer> candBases = new ArrayList<>();
        for (int base = minBase; base <= 9; base++) {
            boolean allValid = true;
            for (String expr : expressions) {
                String[] parts = expr.split(" ");
                if (parts[4].equals("X")) continue;
                if (!isValidExpression(parts[0], parts[2], parts[4], parts[1], base)) {
                    allValid = false;
                    break;
                }
            }
            if (allValid) {
                candBases.add(base);
            }
        }
        // X 값 구하기
        String[] results = new String[exprIncludeX.size()];
        for (int i = 0; i < exprIncludeX.size(); i++) {

            String[] parts = exprIncludeX.get(i).split(" ");

            Set<String> possibleResults = new HashSet<>();
            for (int base : candBases) {
                int numA = toBase(parts[0], base);
                int numB = toBase(parts[2], base);
                int resultPerBase = switch(parts[1]) {
                    case "+" -> numA + numB;
                    case "-" -> numA - numB;
                    default -> throw new IllegalArgumentException(parts[1]);
                };

                possibleResults.add(fromBase(resultPerBase, base));
            }
            if (possibleResults.size() == 1) {
                results[i] = parts[0] + " " + parts[1] + " " + parts[2] + " = " + possibleResults.iterator().next();
            } else {
                results[i] = parts[0] + " " + parts[1] + " " + parts[2] + " = ?";
            }
        }
        return results;
    }
    //가능한 진법 중 최소 진법을 찾기
    public int calculateMinBase(String... nums) {
        int response = 2;
        for (String num : nums) {
            for (char c : num.toCharArray()) {
                if (c != 'X') {
                    int digit = c - '0';
                    response = Math.max(response, digit+1);
                }
            }
        }
        return response;
    }
    //수식에 해당 진법이 성립하는지 확인
    public boolean isValidExpression(String a, String b, String c, String op, int base) {
        int numA = toBase(a, base);
        int numB = toBase(b, base);
        int numC = toBase(c, base);

        return switch(op) {
            case "+" -> numA + numB == numC;
            case "-" -> numA - numB == numC;
            default -> false;
        };
    }
    //base 진법의 수를 10진법으로 수정
    public int toBase(String num, int base) {
        int result = 0;
        int power = 1;
        for (int i = num.length() - 1; i >= 0; i--) {
            int digit = num.charAt(i) - '0';
            result += digit * power;
            power *= base;
        }
        return result;
    }
    //10진법의 수를 base진법으로 수정
    public String fromBase(int num, int base) {
        if (num == 0) return "0";

        //num이 음수인 경우
        boolean negative = num < 0;
        if (negative) num = -num;

        StringBuilder result = new StringBuilder();
        while (num > 0) {
            result.append(num % base);
            num /= base;
        }
        if (negative) result.append("-");

        return result.reverse().toString();
    }
}