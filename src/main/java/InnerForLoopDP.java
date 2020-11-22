import java.util.Arrays;

//https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/
class InnerForLoopDP {
    public int minDifficulty(int[] jobDifficulty, int d) {

        if (jobDifficulty == null || jobDifficulty.length < 1)
            return -1;

        int[] maxFromRight = new int[jobDifficulty.length];
        maxFromRight[maxFromRight.length - 1] = jobDifficulty[jobDifficulty.length - 1];
        for (int i = jobDifficulty.length - 2; i >= 0; i--) {
            maxFromRight[i] = Integer.max(maxFromRight[i + 1], jobDifficulty[i]);
        }

        int[][] memo = new int[d][jobDifficulty.length];
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }

        return dp(jobDifficulty, d - 1, 0, memo, maxFromRight);
    }

    public int dp(int[] jobDifficulty, int daysRemaining, int index, int[][] memo, int[] maxFromRight) {

        if (index == jobDifficulty.length) {
            return -1;
        }

        if (daysRemaining > jobDifficulty.length - index)
            return -1;

        if (daysRemaining == 0) {
            return maxFromRight[index];
        }

        if (memo[daysRemaining][index] >= 0)
            return memo[daysRemaining][index];

        int runningMax = Integer.MIN_VALUE;
        int minCost = Integer.MAX_VALUE;
        for (int i = index; i < jobDifficulty.length; i++) {

            runningMax = Integer.max(runningMax, jobDifficulty[i]);
            int cost = dp(jobDifficulty, daysRemaining - 1, i + 1, memo, maxFromRight);
            if (cost >= 0)
                minCost = Integer.min(minCost, runningMax + cost);
        }

        return memo[daysRemaining][index] = minCost == Integer.MAX_VALUE ? -1 : minCost;
    }

    public static void main(String[] args) {

        System.out.println(new InnerForLoopDP().minDifficulty(new int[]{380, 302, 102, 681, 863, 676, 243, 671, 651, 612, 162, 561, 394, 856, 601, 30, 6, 257, 921, 405, 716, 126, 158, 476, 889, 699, 668, 930, 139, 164, 641, 801, 480, 756, 797, 915, 275, 709, 161, 358, 461, 938, 914, 557, 121, 964, 315}, 10));
        System.out.println(new InnerForLoopDP().minDifficulty(new int[]{10, 1, 1, 3, 10}, 4));
        System.out.println(new InnerForLoopDP().minDifficulty(new int[]{11, 111, 22, 222, 33, 333, 44, 444}, 6));
        System.out.println(new InnerForLoopDP().minDifficulty(new int[]{6, 5, 4, 3, 2, 1}, 2));
    }
}
