class FirstAndLastPosition {
    public int[] searchRange(int[] nums, int target) {

        if (nums == null || nums.length == 0)
            return new int[]{-1, -1};
        if (nums.length == 1)
            return nums[0] == target ? new int[]{0, 0} : new int[]{-1, -1};

        int startPosition = -1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {

            int currentIndex = (right + left) / 2;
            int current = nums[currentIndex];
            if (current > target)
                right = currentIndex - 1;
            else if (current < target)
                left = currentIndex + 1;
            else {
                if (currentIndex == 0 || nums[currentIndex - 1] < target) {
                    startPosition = currentIndex;
                    break;
                } else
                    right = currentIndex - 1;
            }
        }

        int endPosition = -1;
        left = 0;
        right = nums.length - 1;
        while (left <= right) {

            int currentIndex = (right + left) / 2;
            int current = nums[currentIndex];
            if (current > target)
                right = currentIndex - 1;
            else if (current < target)
                left = currentIndex + 1;
            else {
                if (currentIndex == nums.length - 1 || nums[currentIndex + 1] > target) {
                    endPosition = currentIndex;
                    break;
                } else
                    left = currentIndex + 1;
            }
        }

        return new int[]{startPosition, endPosition};
    }
}
