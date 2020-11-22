import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/subarray-sum-equals-k
class SubArraySum {
    public int subarraySum(int[] nums, int k) {

        int[] sumAtIndex = new int[nums.length];

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sumAtIndex[i] = (sum += nums[i]);
        }

        Map<Integer, Integer> sumCounter = new HashMap<>();
        int counter = 0;
        for (int i = 0; i < nums.length; i++) {

            int sumUpto = sumAtIndex[i];
            int diff = sumUpto - k;
            if (diff == 0)
                counter++;

            counter += sumCounter.getOrDefault(diff, 0);

            sumCounter.merge(sumAtIndex[i], 1, Integer::sum);
        }

        return counter;
    }
}
