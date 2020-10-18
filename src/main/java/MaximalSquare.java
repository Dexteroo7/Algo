//https://leetcode.com/problems/maximal-square
class MaximalSquare {
    public int maximalSquare(char[][] matrix) {

        int[][] dp = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            dp[i] = new int[matrix[i].length];
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {

                if (matrix[row][column] != '1')
                    continue;

                if (row == 0 || column == 0)
                    dp[row][column] = 1;
                else {

                    int fromTop = dp[row - 1][column];
                    int fromLeft = dp[row][column - 1];
                    int fromDiag = dp[row - 1][column - 1];
                    dp[row][column] = Integer.min(fromTop, Integer.min(fromLeft, fromDiag)) + 1;
                }
            }
        }

        int max = 0;
        for (int row = 0; row < dp.length; row++) {
            for (int column = 0; column < dp[row].length; column++) {
                max = Integer.max(max, dp[row][column]);
            }
        }

        return max * max;
    }
}
