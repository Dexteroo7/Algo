import java.util.Arrays;

//https://leetcode.com/problems/prison-cells-after-n-days/
class UselessPattern {
    public int[] prisonAfterNDays(int[] cells, int n) {

        if (n == 0)
            return cells;
        if (cells.length == 2)
            return cells;

        int[] clone = new int[cells.length];

        int j;
        for (j = 0; j < n; j++) {

            int prevNew = 0;
            if (j == 1)
                System.arraycopy(cells, 0, clone, 0, cells.length);
            for (int i = 1; i < cells.length - 1; i++) {

                int temp = prevNew;
                prevNew = cells[i - 1] == cells[i + 1] ? 1 : 0;
                cells[i - 1] = temp;
            }
            cells[cells.length - 2] = prevNew;
            cells[cells.length - 1] = 0;

            if (j > 1) {
                boolean cycle = true;
                for (int i = 0; i < cells.length; i++) {
                    if (cells[i] != clone[i]) {
                        cycle = false;
                        break;
                    }
                }

                System.out.println(Arrays.toString(cells));
                if (cycle) {
                    n = ((n-1) % j);
                    for (j = 0; j < n; j++) {

                        prevNew = 0;
                        for (int i = 1; i < cells.length - 1; i++) {

                            int temp = prevNew;
                            prevNew = cells[i - 1] == cells[i + 1] ? 1 : 0;
                            cells[i - 1] = temp;
                        }
                        cells[cells.length - 2] = prevNew;
                        cells[cells.length - 1] = 0;
                    }
                    break;
                }
            }
        }

        return cells;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new UselessPattern().prisonAfterNDays(
                new int[]{0,0,0,1,1,0,1,0}, 574)));
    }
}
