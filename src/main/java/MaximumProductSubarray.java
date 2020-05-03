import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class MaximumProductSubarray {

    public static int maxProduct(int[] nums) {

        if (nums == null || nums.length == 0)
            return -1;
        if (nums.length == 1)
            return nums[0];

        //0 serves as a boundary
        //between boundaries we recheck only if product is negative
        int overAllMax = Integer.MIN_VALUE;
        int productSoFar = 1;
        int start = 0;
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == 0) {

                if (productSoFar < 0) {
                    overAllMax = Integer.max(overAllMax, maxProduct(nums, start, i - 1, productSoFar));
                }
                overAllMax = Integer.max(overAllMax, 0);
                productSoFar = 1;
                start = i + 1;

            } else {
                productSoFar = productSoFar * nums[i];
                overAllMax = Integer.max(overAllMax, productSoFar);
            }
        }

        if (productSoFar < 0) {
            overAllMax = Integer.max(overAllMax, maxProduct(nums, start, nums.length - 1, productSoFar));
        }

        return overAllMax;
    }

    public static int maxProduct(int[] nums, int start, int end, int product) {

        int productFromLeft = product;
        for (int j = start; j < end; j++) {
            productFromLeft = productFromLeft / nums[j];
            if (nums[j] < 0) {
                break;
            }
        }

        int productFromRight = product;
        for (int j = end; j > start; j--) {
            productFromRight = productFromRight / nums[j];
            if (nums[j] < 0) {
                break;
            }
        }

        return Integer.max(productFromLeft, productFromRight);
    }

    public static void main(String[] args) {

        System.out.println(maxProduct(new int[]{-2}));
        System.out.println(maxProduct(new int[]{-1, -2}));
        System.out.println(maxProduct(new int[]{-1, -2, -3}));

        System.out.println(maxProduct(new int[]{1}));
        System.out.println(maxProduct(new int[]{1, 1}));
        System.out.println(maxProduct(new int[]{1, 2}));
        System.out.println(maxProduct(new int[]{1, 2, 10}));

        System.out.println(maxProduct(new int[]{1, 2, 10, 0, 1}));
        System.out.println(maxProduct(new int[]{1, 2, 10, 0, 100}));
        System.out.println(maxProduct(new int[]{1, 2, 10, 0, 100, 1}));
        System.out.println(maxProduct(new int[]{1, 2, 10, 0, 100, 1, 2}));
        System.out.println(maxProduct(new int[]{1, 2, 10, 0, 100, 1, 2, 0, 0}));
        System.out.println(maxProduct(new int[]{1, 2, 10, 0, 100, 1, 2, 0, 0, 1000}));

        System.out.println(maxProduct(new int[]{0, -1, -1, -1, -2, -3, 0}));
        System.out.println(maxProduct(new int[]{-1, 0, -2, -3}));
        System.out.println(maxProduct(new int[]{-1, 0, -2, 4}));
        System.out.println(maxProduct(new int[]{-1, 0, -2}));
        System.out.println(maxProduct(new int[]{2, 3, -2, 4}));
    }
}
