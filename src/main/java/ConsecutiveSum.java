import java.util.Arrays;
import java.util.TreeMap;

//https://leetcode.com/problems/sliding-window-maximum/solution/
class ConsecutiveSum {
    public int[] maxSlidingWindow(int[] nums, int k) {

        int[] slidingMax = new int[nums.length - k + 1];

        int left = 0;
        int pos = 0;

        TreeMap<Integer, Integer> slidingCounter = new TreeMap<>();

        for (int right = 0; right < nums.length; right++) {

            if (right - left + 1 > k) {
                slidingMax[pos] = slidingCounter.lastKey();
                slidingCounter.compute(nums[left], (key, value) -> (value == 1) ? null : value - 1);
                left++;
                pos++;
            }
            slidingCounter.merge(nums[right], 1, Integer::sum);
        }

        slidingMax[pos] = slidingCounter.lastKey();
        return slidingMax;
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(new ConsecutiveSum().maxSlidingWindow(
                new int[]{4, -2},
                2)));
    }
}
