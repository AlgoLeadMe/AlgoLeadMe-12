import java.util.*;

class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;

        int start = changeToSec(h1, m1, s1);
        int end = changeToSec(h2, m2, s2);


        for (int i = start; i < end; i++) {
            List<Double> current = getDegree(i);
            List<Double> next = getDegree(i+1);

            boolean hMatch = hourMatch(current, next);
            boolean mMatch = minMatch(current, next);

            // 초침이 분침과 시침과 겹침이 발생했을 때,
            if(hMatch && mMatch){
                // 시침과 분침의 각도가 같다면 +1만 해줘야함
                if(Double.compare(next.get(0),next.get(1)) == 0) answer++;
                    // 아니라면 +2
                else answer +=2;
            }
            // 둘 중 하나라도 겹치면 +1
            else if(hMatch || mMatch) answer++;
        }

        if(start == 0 || start == 43200) answer++;

        return answer;
    }
    public int changeToSec(int h, int m, int s) {
        return 3600*h + 60*m + s;
    }

    public List<Double> getDegree(int t) {
        int h = t/3600;
        int m = (t%3600) / 60;
        int s = (t%3600) % 60;

        Double hDegree = (h%12) * 30 + m*0.5 + s*(1.0/120);
        Double mDegree = m*6 + s*(0.1);
        Double sDegree = s*6.0;

        return List.of(hDegree, mDegree, sDegree);
    }
    public boolean hourMatch(List<Double> current, List<Double> next) {
        if (Double.compare(current.get(0), current.get(2)) > 0
                && Double.compare(next.get(0), next.get(2)) <= 0) {
            return true;
        }
        if (Double.compare(current.get(2), 354) == 0
                && Double.compare(current.get(0), 354) > 0) {
            return true;
        }
        return false;
    }
    public boolean minMatch(List<Double> current, List<Double> next) {
        if (Double.compare(current.get(1), current.get(2)) > 0
                && Double.compare(next.get(1), next.get(2)) <= 0) {
            return true;
        }
        if (Double.compare(current.get(2), 354) == 0
                && Double.compare(current.get(1), 354) > 0) {
            return true;
        }
        return false;
    }
}