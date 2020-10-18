import java.util.Arrays;

//https://leetcode.com/problems/product-of-array-except-self/submissions/
class ProductOfArray {
    public int[] productExceptSelf(int[] nums) {

        if (nums == null || nums.length < 2)
            return nums;

        int[] output = new int[nums.length];
        output[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            output[i] = output[i - 1] * nums[i];
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            nums[i] = nums[i] * nums[i + 1];
        }

        output[output.length - 1] = output[output.length - 2];

        for (int i = output.length - 2; i > 0; i--) {

            output[i] = output[i - 1] * nums[i + 1];
        }

        output[0] = nums[1];


        return output;
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(new ProductOfArray().productExceptSelf(new int[]{1, 2, 3, 4})));
    }
}
