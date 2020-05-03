import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Arrays.sort;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

//https://leetcode.com/problems/3sum/submissions/
class ThreeSum {

    public static List<List<Integer>> threeSum(int[] nums) {

        if (nums.length < 3)
            return emptyList();
        if (nums.length == 3) {
            return nums[0] + nums[1] + nums[2] == 0 ? singletonList(asList(nums[0], nums[1], nums[2])) : emptyList();
        }

        //sort in asc order
        sort(nums);
//        System.out.println(Arrays.toString(nums));

        List<List<Integer>> toReturn = new ArrayList<>();
        for (int currentIndex = 0; currentIndex < nums.length; currentIndex++) {

            //not possible with only positive numbers
            if (nums[currentIndex] > 0)
                break;

            //will give same results, so skip
            if (currentIndex > 0 && nums[currentIndex] == nums[currentIndex - 1])
                continue;

            int lowerIndex = currentIndex + 1;
            int upperIndex = nums.length - 1;
            //we increase lower and decrease upper
            while (lowerIndex < upperIndex) {

                int sum = nums[currentIndex] + nums[lowerIndex] + nums[upperIndex];

                if (sum > 0) {
                    //decrease upper, as we need to bring down the sum
                    upperIndex--;
                } else if (sum == 0) {
                    toReturn.add(asList(nums[currentIndex], nums[lowerIndex], nums[upperIndex]));
                    //increase/decrease till same value
                    lowerIndex++;
                    while (lowerIndex < upperIndex && nums[lowerIndex] == nums[lowerIndex - 1])
                        lowerIndex++;
                    upperIndex--;
                    while (lowerIndex < upperIndex && nums[upperIndex] == nums[upperIndex + 1])
                        upperIndex--;
                } else {
                    //increase lower, as we need to bring up the sum
                    lowerIndex++;
                }
            }
        }

        return toReturn;
    }

//    public static void main(String[] args) {
//
//        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
//
//        System.out.println(threeSum(new int[]{-1, 0, 1}));
//        System.out.println(threeSum(new int[]{-1, 0, 1, 1, -1}));
//        System.out.println(threeSum(new int[]{-1, 0, 1, 1, 2, -1}));
//
//        System.out.println(threeSum(new int[]{10, 10, -10, -10, 0, 0, -20}));
//        System.out.println(threeSum(new int[]{1,1,1,1,-1}));
//        System.out.println(threeSum(new int[]{1,1,1,1,-2}));
//    }
}
