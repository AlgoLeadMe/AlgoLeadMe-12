import java.util.*;

public class Main {
    static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        map = new int[9][9];
        List<int[]> blank = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 0) {
                    blank.add(new int[]{i, j});
                }
            }
        }
        sudoku(blank, 0);
    }
    public static void sudoku(List<int[]> blank, int count) {
        if (count == blank.size()) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            System.exit(0);
        }

        int[] coor = blank.get(count);
        List<Integer> candiNum = checkCandi(coor[0], coor[1]);

        for (int num : candiNum) {
            map[coor[0]][coor[1]] = num;
            sudoku(blank, count+1);
            map[coor[0]][coor[1]] = 0;
        }
    }
    /*map[row][col]에 들어갈 수 있는 숫자 모음*/
    public static List<Integer> checkCandi(int row, int col) {
        boolean[] used = new boolean[10];

        for (int i = 0; i < 9; i++) {
            used[map[row][i]] = true;
            used[map[i][col]] = true;
        }

        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                used[map[i][j]] = true;
            }
        }
        List<Integer> candiNum = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            if (!used[i]) candiNum.add(i);
        }
        return candiNum;
    }
}
