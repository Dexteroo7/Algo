import java.util.Arrays;

class Search_in_Rotated_Sorted_Array {
    public int search(int[] nums, int target) {

        if (nums == null)
            return -1;
        if (nums.length == 0)
            return -1;
        if (nums.length == 1)
            return nums[0] == target ? 0 : -1;

        int currentPivot = nums.length / 2;
        int left = 0, right = nums.length - 1;
        while (!isPivot(nums, currentPivot)) {

            //pivot is to the left
            if (nums[0] > nums[currentPivot]) {
                right = currentPivot;
                currentPivot = left + ((currentPivot + 1 - left) / 2);
            }
            //pivot is to the right
            else if (nums[nums.length - 1] < nums[currentPivot]) {
                left = currentPivot;
                currentPivot = currentPivot + ((right + 1 - currentPivot) / 2);
            } else {
                currentPivot = 0;
                break;
            }
        }

        //now we can do binary search in 2 parts of the array ?
        int result = Arrays.binarySearch(nums, 0, currentPivot, target);
        if (result >= 0)
            return result;
        result = Arrays.binarySearch(nums, currentPivot, nums.length, target);
        if (result >= 0)
            return result;
        return -1;
    }

    public boolean isPivot(int[] nums, int index) {

        if (index == 0)
            return nums[index] > nums[index + 1];
        if (index == nums.length - 1)
            return nums[index] < nums[index - 1];
        else
            return nums[index - 1] > nums[index] && nums[index] < nums[index + 1];
    }
}
