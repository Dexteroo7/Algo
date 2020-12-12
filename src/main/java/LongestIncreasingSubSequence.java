import java.util.Arrays;

//https://leetcode.com/problems/longest-increasing-subsequence/
class LongestIncreasingSubSequence {


    public int lengthOfLIS(int[] nums) {

        int[] dp = new int[nums.length];
        int len = 0;

        for (int num : nums) {

            int index = Arrays.binarySearch(dp, 0, len, num);
            index = index < 0 ? -(index + 1) : index;
            dp[index] = num;
            if (index == len)
                len++;
        }

        return len;
    }
}
